package com.github.lithualien.advertisement.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority extends BaseEntity implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
