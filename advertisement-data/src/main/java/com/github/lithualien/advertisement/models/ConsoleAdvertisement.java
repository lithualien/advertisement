package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;
import org.hibernate.search.annotations.*;

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
@Table(name = "console_advertisements", schema = "advertisement")
public class ConsoleAdvertisement extends Advertisement {

    @Mapping("this")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consoleAdvertisement")
    private List<ConsoleImage> images = new ArrayList<>();

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String model;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String memory;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String color;
}
