package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_advertisement")
public class TabletAdvertisement extends Advertisement {

    private String manufacturer;

    private String model;

    private String color;

    private String memory;

    @Column(name = "screen_size")
    private String screenSize;

}
