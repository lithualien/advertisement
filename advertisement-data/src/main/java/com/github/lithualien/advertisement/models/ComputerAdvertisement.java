package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.Advertisement;
import com.github.lithualien.advertisement.models.superclass.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "computer_advertisements")
public class ComputerAdvertisement extends Advertisement {

    @Mapping("this")
    @OneToMany(mappedBy = "computerAdvertisement", cascade = CascadeType.ALL)
    private List<ComputerImage> images = new LinkedList<>();

    @Mapping("this")
    @ManyToOne
    private City city;

    private String cpu;

    private String gpu;

    private String ram;

    private String memory;

    private String motherboard;

}
