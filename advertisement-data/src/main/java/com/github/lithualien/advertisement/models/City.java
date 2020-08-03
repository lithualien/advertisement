package com.github.lithualien.advertisement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.lithualien.advertisement.models.superclass.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Indexed
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO, termVector = TermVector.YES)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    private County county;

    public City(Long id, String city, County county) {
        super(id);
        this.city = city;
        this.county = county;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<UserPersonalInformation> userPersonalInformation = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<ComputerAdvertisement> computerAdvertisements = new HashSet<>();

}
