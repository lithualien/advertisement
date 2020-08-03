package com.github.lithualien.advertisement.repositories.impl;

import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.repositories.SearchRepository;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SearchRepositoryImpl implements SearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<ComputerAdvertisement> findAllBaseOnSearch(Pageable pageable, String cpu, String gpu, String ram,
                                                           String memory, String motherboard) {

        Query fuzzyQuery = getQueryBuilder(ComputerAdvertisement.class)
                .bool()
                .should(
                        getQueryBuilder(ComputerAdvertisement.class)
                                .keyword()
                                .fuzzy()
                                .withEditDistanceUpTo(1)
                                .withPrefixLength(0)
                                .onField("cpu")
                                .matching(cpu)
                                .createQuery()
                )
                .should(
                        getQueryBuilder(ComputerAdvertisement.class)
                                .keyword()
                                .fuzzy()
                                .withEditDistanceUpTo(1)
                                .withPrefixLength(0)
                                .onField("gpu")
                                .matching(gpu)
                                .createQuery()
                )
                .should(
                        getQueryBuilder(ComputerAdvertisement.class)
                                .keyword()
                                .fuzzy()
                                .withEditDistanceUpTo(1)
                                .withPrefixLength(0)
                                .onField("ram")
                                .matching(ram)
                                .createQuery()
                )
                .should(
                        getQueryBuilder(ComputerAdvertisement.class)
                                .keyword()
                                .fuzzy()
                                .withEditDistanceUpTo(1)
                                .withPrefixLength(0)
                                .onField("memory")
                                .matching(memory)
                                .createQuery()
                )
                .should(
                        getQueryBuilder(ComputerAdvertisement.class)
                                .keyword()
                                .fuzzy()
                                .withEditDistanceUpTo(1)
                                .withPrefixLength(0)
                                .onField("motherboard")
                                .matching(motherboard)
                                .createQuery()
                )
                .createQuery();

        List<ComputerAdvertisement> advertisements = getJpaQuery(fuzzyQuery, ComputerAdvertisement.class, pageable).getResultList();

        return new PageImpl<>(advertisements, pageable, advertisements.size());
    }

    private<T> FullTextQuery getJpaQuery(Query luceneQuery, Class<T> object, Pageable pageable) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager
                .createFullTextQuery(luceneQuery, object)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
    }

    private<T> QueryBuilder getQueryBuilder(Class<T> object) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(object)
                .get();
    }
}
