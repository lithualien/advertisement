package com.github.lithualien.advertisement.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @ManyToOne
    private Category category;

}
