package com.github.lithualien.advertisement.models;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public Role(String role) {
        this.role = role;
    }

    public void addUser(User user) {
        user.setRole(this);
        this.users.add(user);
    }

}
