package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_personal_information", schema = "advertisement")
public class UserPersonalInformation extends BaseEntity {

    private String name;

    private String email;
    private String number;

    @Mapping("this")
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
