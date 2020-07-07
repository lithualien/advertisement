package com.github.lithualien.advertisement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_personal_information")
public class UserPersonalInformation extends BaseEntity {

    private String name;

    private String email;
    private String number;

    @Mapping("this")
    @JsonManagedReference
    @ManyToOne
    private City city;

    @JsonBackReference
    @ManyToOne
    private User user;

}