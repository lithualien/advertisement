package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    User findById(Long id);
    User findByUsername(String username);

}
