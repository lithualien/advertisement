package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;
import org.hibernate.search.annotations.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Indexed
@Entity
@Table(name = "monitor_advertisement")
public class MonitorAdvertisement extends Advertisement {

    @Mapping("this")
    @IndexedEmbedded
    @ManyToOne
    private City city;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String brand;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String model;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String resolution;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String refreshRate;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String responseTime;

}
