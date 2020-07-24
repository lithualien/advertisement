package com.github.lithualien.advertisement.models.superclass;

import com.github.lithualien.advertisement.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Image extends BaseEntity {

    protected URL url;

    @ManyToOne
    protected User user;

}
