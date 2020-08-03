package com.github.lithualien.advertisement.models.superclass;

import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.models.Type;
import com.github.lithualien.advertisement.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Advertisement extends BaseEntity {

    protected String article;

    protected Double price;

    @Lob
    protected String description;

    @OneToOne
    @IndexedEmbedded
    protected Type type;

    @ManyToOne
    @IndexedEmbedded
    protected SubCategory subCategory;

    @ManyToOne
    @IndexedEmbedded
    protected User user;


}
