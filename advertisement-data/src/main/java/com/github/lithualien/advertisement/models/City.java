package com.github.lithualien.advertisement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    private String city;

    @ManyToOne(cascade = CascadeType.ALL)
    private County county;

    public City(Long id, String city, County county) {
        super(id);
        this.city = city;
        this.county = county;
    }

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<UserPersonalInformation> userPersonalInformation = new HashSet<>();

}
