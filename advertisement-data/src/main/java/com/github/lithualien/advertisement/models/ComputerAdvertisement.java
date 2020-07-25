package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "computer_advertisements")
public class ComputerAdvertisement extends Advertisement {

    @Mapping("this")
    @OneToMany(mappedBy = "computerAdvertisement", cascade = CascadeType.ALL)
    private List<Image> images = new LinkedList<>();

    @ManyToOne
    private City city;

    private String cpu;

    private String gpu;

    private String ram;

    private String memory;

    private String motherboard;

}
