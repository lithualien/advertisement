package com.github.lithualien.advertisement.models.superclass;

import com.github.lithualien.advertisement.models.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Advertisement extends BaseEntity {

    protected String article;

    protected Double price;

    @Lob
    protected String description;

    @Lob
    protected Byte[] images;

    @OneToOne
    protected Type type;

}
