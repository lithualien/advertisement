package com.github.lithualien.advertisement.repositories.impl;

import com.github.lithualien.advertisement.models.*;
import com.github.lithualien.advertisement.repositories.SearchRepository;
import com.github.lithualien.advertisement.vo.v1.advertisement.*;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
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
    public Page<ComputerAdvertisement> searchComputers(Pageable pageable, ComputerAdvertisementSearchVO searchVO) {

        QueryBuilder queryBuilder = getQueryBuilder(ComputerAdvertisement.class);

        Sort sort = getSort();

        BooleanJunction<?> booleanJunction = queryBuilder.bool();

        if(!searchVO.getSubCategory().equals("")){
            booleanJunction
                    .must(
                            queryBuilder
                                    .keyword()
                                    .onField("subCategory.subCategory")
                                    .matching(searchVO.getSubCategory())
                                    .createQuery()
                    );
        }

        if(!searchVO.getCity().equals("")) {
            booleanJunction
                    .must(
                            queryBuilder
                                    .keyword()
                                    .onField("city.city")
                                    .matching(searchVO.getCity())
                                    .createQuery()
                    );
        }

        if(!searchVO.getCpu().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .fuzzy()
                                    .withEditDistanceUpTo(1)
                                    .withPrefixLength(0)
                                    .onField("cpu")
                                    .matching(searchVO.getCpu())
                                    .createQuery()
                    );
        }

        if(!searchVO.getGpu().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .fuzzy()
                                    .withEditDistanceUpTo(1)
                                    .withPrefixLength(0)
                                    .onField("gpu")
                                    .matching(searchVO.getGpu())
                                    .createQuery()
                    );
        }

        if(!searchVO.getRam().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .fuzzy()
                                    .withEditDistanceUpTo(1)
                                    .withPrefixLength(0)
                                    .onField("ram")
                                    .matching(searchVO.getRam())
                                    .createQuery()
                    );
        }

        if(!searchVO.getMemory().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .fuzzy()
                                    .withEditDistanceUpTo(1)
                                    .withPrefixLength(0)
                                    .onField("memory")
                                    .matching(searchVO.getMemory())
                                    .createQuery()
                    );
        }

        if(!searchVO.getMotherboard().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .fuzzy()
                                    .withEditDistanceUpTo(1)
                                    .withPrefixLength(0)
                                    .onField("motherboard")
                                    .matching(searchVO.getMotherboard())
                                    .createQuery()
                    );
        }

        Query fuzzyQuery = booleanJunction.createQuery();

        List<ComputerAdvertisement> advertisements
                = getJpaQuery(fuzzyQuery, ComputerAdvertisement.class, pageable)
                    .setSort(sort)
                    .getResultList();


        return new PageImpl<>(advertisements, pageable, advertisements.size());


    }

    @Override
    public Page<PhoneAdvertisement> searchPhones(Pageable pageable, PhoneAdvertisementSearchVO searchVO) {

        QueryBuilder queryBuilder = getQueryBuilder(PhoneAdvertisement.class);

        BooleanJunction<?> booleanJunction = queryBuilder.bool();

        Sort sort = getSort();

        if(!searchVO.getSubCategory().equals("")){
            booleanJunction
                    .must(
                            queryBuilder
                                    .keyword()
                                    .onField("subCategory.subCategory")
                                    .matching(searchVO.getSubCategory())
                                    .createQuery()
                    );
        }


        if(!searchVO.getCity().equals("")) {
            booleanJunction
                    .must(
                            queryBuilder
                            .keyword()
                            .onField("city.city")
                            .matching(searchVO.getCity())
                            .createQuery()
                    );
        }

        if(!searchVO.getManufacturer().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                            .keyword()
                            .fuzzy()
                            .onField("manufacturer")
                            .matching(searchVO.getManufacturer())
                            .createQuery()
                    );
        }

        if(!searchVO.getModel().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .fuzzy()
                                    .onField("model")
                                    .matching(searchVO.getModel())
                                    .createQuery()
                    );
        }

        if(!searchVO.getOs().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .fuzzy()
                                    .onField("os")
                                    .matching(searchVO.getOs())
                                    .createQuery()
                    );
        }

        if(!searchVO.getCamera().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .fuzzy()
                                    .onField("camera")
                                    .matching(searchVO.getCamera())
                                    .createQuery()
                    );
        }

        if(!searchVO.getRam().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .fuzzy()
                                    .onField("ram")
                                    .matching(searchVO.getRam())
                                    .createQuery()
                    );
        }

        if(!searchVO.getMemory().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .fuzzy()
                                    .onField("memory")
                                    .matching(searchVO.getMemory())
                                    .createQuery()
                    );
        }

        Query query = booleanJunction.createQuery();

        List<PhoneAdvertisement> phoneAdvertisements
                = getJpaQuery(query, PhoneAdvertisement.class, pageable)
                    .setSort(sort)
                    .getResultList();

        return new PageImpl<>(phoneAdvertisements, pageable, phoneAdvertisements.size());
    }

    @Override
    public Page<ConsoleAdvertisement> searchConsole(Pageable pageable, ConsoleAdvertisementSearchVO searchVO) {

        QueryBuilder queryBuilder = getQueryBuilder(ConsoleAdvertisement.class);

        BooleanJunction<?> booleanJunction = queryBuilder.bool();

        Sort sort = getSort();

        if(!searchVO.getSubCategory().equals("")){
            booleanJunction
                    .must(
                            queryBuilder
                                    .keyword()
                                    .onField("subCategory.subCategory")
                                    .matching(searchVO.getSubCategory())
                                    .createQuery()
                    );
        }


        if(!searchVO.getCity().equals("")) {
            booleanJunction
                    .must(
                            queryBuilder
                                    .keyword()
                                    .onField("city.city")
                                    .matching(searchVO.getCity())
                                    .createQuery()
                    );
        }

        if(!searchVO.getModel().equals("")){
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .onField("model")
                                    .matching(searchVO.getModel())
                                    .createQuery()
                    );
        }

        if(!searchVO.getMemory().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .onField("memory")
                                    .matching(searchVO.getMemory())
                                    .createQuery()
                    );
        }

        if(!searchVO.getColor().equals("")){
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .onField("color")
                                    .matching(searchVO.getColor())
                                    .createQuery()
                    );
        }

        Query query = booleanJunction.createQuery();

        List<ConsoleAdvertisement> advertisements
                = getJpaQuery(query, PhoneAdvertisement.class, pageable)
                .setSort(sort)
                .getResultList();

        return new PageImpl<>(advertisements, pageable, advertisements.size());
    }

    @Override
    public Page<ExternalDeviceAdvertisement> searchExternalDevice(Pageable pageable, ExternalDeviceAdvertisementSearchVO searchVO) {

        QueryBuilder queryBuilder = getQueryBuilder(ExternalDeviceAdvertisement.class);

        BooleanJunction<?> booleanJunction = queryBuilder.bool();

        Sort sort = getSort();

        if(!searchVO.getSubCategory().equals("")){
            booleanJunction
                    .must(
                            queryBuilder
                                    .keyword()
                                    .onField("subCategory.subCategory")
                                    .matching(searchVO.getSubCategory())
                                    .createQuery()
                    );
        }


        if(!searchVO.getCity().equals("")) {
            booleanJunction
                    .must(
                            queryBuilder
                                    .keyword()
                                    .onField("city.city")
                                    .matching(searchVO.getCity())
                                    .createQuery()
                    );
        }

        if(!searchVO.getBrand().equals("")){
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .onField("brand")
                                    .matching(searchVO.getBrand())
                                    .createQuery()
                    );
        }


        if(!searchVO.getWireless().equals("")) {
            booleanJunction
                    .must(
                            queryBuilder
                                    .keyword()
                                    .onField("wireless")
                                    .matching(searchVO.getWireless())
                                    .createQuery()
                    );
        }

        Query query = booleanJunction.createQuery();

        List<ExternalDeviceAdvertisement> advertisements
                = getJpaQuery(query, PhoneAdvertisement.class, pageable)
                .setSort(sort)
                .getResultList();

        return new PageImpl<>(advertisements, pageable, advertisements.size());
    }

    @Override
    public Page<MonitorAdvertisement> searchMonitor(Pageable pageable, MonitorAdvertisementSearchVO searchVO) {

        QueryBuilder queryBuilder = getQueryBuilder(MonitorAdvertisement.class);

        BooleanJunction<?> booleanJunction = queryBuilder.bool();

        Sort sort = getSort();

        if(!searchVO.getSubCategory().equals("")){
            booleanJunction
                    .must(
                            queryBuilder
                                    .keyword()
                                    .onField("subCategory.subCategory")
                                    .matching(searchVO.getSubCategory())
                                    .createQuery()
                    );
        }


        if(!searchVO.getCity().equals("")) {
            booleanJunction
                    .must(
                            queryBuilder
                                    .keyword()
                                    .onField("city.city")
                                    .matching(searchVO.getCity())
                                    .createQuery()
                    );
        }

        if(!searchVO.getBrand().equals("")){
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .onField("brand")
                                    .matching(searchVO.getBrand())
                                    .createQuery()
                    );
        }


        if(!searchVO.getModel().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .onField("model")
                                    .matching(searchVO.getModel())
                                    .createQuery()
                    );
        }

        if(!searchVO.getResolution().equals("")){
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .onField("resolution")
                                    .matching(searchVO.getResolution())
                                    .createQuery()
                    );
        }


        if(!searchVO.getRefreshRate().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .onField("refreshRate")
                                    .matching(searchVO.getRefreshRate())
                                    .createQuery()
                    );
        }

        if(!searchVO.getResponseTime().equals("")) {
            booleanJunction
                    .should(
                            queryBuilder
                                    .keyword()
                                    .onField("responseTime")
                                    .matching(searchVO.getResponseTime())
                                    .createQuery()
                    );
        }

        Query query = booleanJunction.createQuery();

        List<MonitorAdvertisement> advertisements
                = getJpaQuery(query, PhoneAdvertisement.class, pageable)
                .setSort(sort)
                .getResultList();

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

    private Sort getSort(){
        return new Sort(SortField.FIELD_SCORE, new SortField("id", SortField.Type.STRING, true));
    }
}
