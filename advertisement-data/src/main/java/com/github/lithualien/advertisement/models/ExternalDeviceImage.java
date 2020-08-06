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
@Table(name = "external_device_images")
public class ExternalDeviceImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    private ExternalDeviceAdvertisement externalDeviceAdvertisement;

}
