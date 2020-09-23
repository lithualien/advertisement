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
import java.util.LinkedList;
import java.util.List;

@Indexed
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "computer_advertisements", schema = "advertisement")
public class ComputerAdvertisement extends Advertisement {

    @Mapping("this")
    @OneToMany(mappedBy = "computerAdvertisement", cascade = CascadeType.ALL)
    private List<ComputerImage> images = new LinkedList<>();

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String cpu;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String gpu;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String ram;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String memory;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String motherboard;

}
