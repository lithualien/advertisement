package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
