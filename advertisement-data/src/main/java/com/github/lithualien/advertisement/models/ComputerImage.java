package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "computer_images")
public class ComputerImage extends Image {

    @ManyToOne
    private ComputerAdvertisement computerAdvertisement;
}
