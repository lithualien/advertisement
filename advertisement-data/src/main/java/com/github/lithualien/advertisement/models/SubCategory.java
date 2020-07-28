package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sub_categories")
public class SubCategory extends BaseEntity {

    @Column(name = "sub_category")
    private String subCategory;

    @Mapping("this")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Mapping("this")
    @OneToMany(mappedBy = "subCategory")
    private Set<ComputerAdvertisement> advertisements = new HashSet<>();

}
