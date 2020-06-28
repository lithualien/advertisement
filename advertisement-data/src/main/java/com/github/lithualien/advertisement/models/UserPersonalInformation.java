package com.github.lithualien.advertisement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_personal_information")
public class UserPersonalInformation extends BaseEntity {

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String email;
    private String number;

    @JsonManagedReference
    @OneToOne
    private City city;

    @JsonBackReference
    @OneToOne
    private User user;

}
