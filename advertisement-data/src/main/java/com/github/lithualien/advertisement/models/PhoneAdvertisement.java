package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;
import org.hibernate.search.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Indexed
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone_advertisements", schema = "advertisement")
public class PhoneAdvertisement extends Advertisement {

    @Mapping("this")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phoneAdvertisement")
    private List<PhoneImage> phoneImages = new ArrayList<>();

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String manufacturer;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String model;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String os;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String camera;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String ram;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String memory;

}
