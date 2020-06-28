package com.github.lithualien.advertisement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    private String city;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private County county;

    @JsonBackReference
    @OneToOne
    private UserPersonalInformation userPersonalInformation;
}
