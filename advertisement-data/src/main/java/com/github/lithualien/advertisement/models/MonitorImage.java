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
@Table(name = "monitor_images", schema = "advertisement")
public class MonitorImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    private MonitorAdvertisement monitorAdvertisement;

}
