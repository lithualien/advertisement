package com.github.lithualien.advertisement.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "county")
    private Set<City> cities = new HashSet<>();

}
