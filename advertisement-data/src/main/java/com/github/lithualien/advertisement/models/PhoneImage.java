package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone_images", schema = "advertisement")
public class PhoneImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    private PhoneAdvertisement phoneAdvertisement;

}
