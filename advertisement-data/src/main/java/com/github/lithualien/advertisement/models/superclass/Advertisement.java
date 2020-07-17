package com.github.lithualien.advertisement.models.superclass;

import com.github.lithualien.advertisement.models.BaseEntity;
import com.github.lithualien.advertisement.models.Type;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@MappedSuperclass
public class Advertisement extends BaseEntity {

    private String article;

    private Double price;

    private String description;

    @Lob
    private Byte[] images;

    @OneToOne
    private Type type;

    @Column(name = "created_at")
    private LocalDate createDate;

    @Column(name = "updated_at")
    private LocalDate updateDate;

    @Column(name = "sold_at")
    private LocalDate soldDate;

}
