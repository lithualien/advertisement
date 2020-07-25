package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

import javax.persistence.*;
import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Image extends BaseEntity {

    private URL url;

    @ManyToOne
    private User user;

    @ManyToOne
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    private ComputerAdvertisement computerAdvertisement;

}
