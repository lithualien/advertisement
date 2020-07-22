package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "types")
public class Type extends BaseEntity {

    private String type;

}
