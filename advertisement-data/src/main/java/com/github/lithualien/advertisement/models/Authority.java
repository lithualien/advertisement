package com.github.lithualien.advertisement.models;

import com.github.lithualien.advertisement.models.superclass.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authorities", schema = "advertisement")
public class Authority extends BaseEntity implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

}
