package com.github.lithualien.advertisement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "counties")
public class County extends BaseEntity {

    private String county;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "county")
    private Set<City> cities = new HashSet<>();

}
