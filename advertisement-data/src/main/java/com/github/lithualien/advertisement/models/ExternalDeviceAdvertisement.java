package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Indexed
@Entity
@Table(name = "external_device_advertisements", schema = "advertisement")
public class ExternalDeviceAdvertisement extends Advertisement {

    @Mapping("this")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "externalDeviceAdvertisement")
    private List<ExternalDeviceImage> images = new ArrayList<>();

    private String brand;

    private boolean wireless;
}
