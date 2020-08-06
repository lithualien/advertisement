package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Indexed
@Entity
@Table(name = "external_device_advertisements")
public class ExternalDeviceAdvertisement extends Advertisement {

    @Mapping("this")
    @IndexedEmbedded
    @ManyToOne
    private City city;

    private String brand;

    private boolean wireless;
}
