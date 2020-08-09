package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Indexed
@Entity
@Table(name = "tv_advertisements")
public class TVAdvertisement extends Advertisement {

    @Mapping("this")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tvAdvertisement")
    private List<TVImage> images = new ArrayList<>();

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String brand;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String diameter;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String resolution;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private boolean smartTV;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private boolean threeD;

}
