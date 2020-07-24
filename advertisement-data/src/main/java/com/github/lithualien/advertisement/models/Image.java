package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Image extends BaseEntity {

    private URL url;

    @Mapping("this")
    @ManyToOne
    private User user;

    @Mapping("this")
    @ManyToOne
    private SubCategory subCategory;

    @Mapping("this")
    @ManyToOne
    private ComputerAdvertisement computerAdvertisement;


}
