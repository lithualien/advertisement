package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select user from User user where user.username = :username")
    User findUserByUsername(String username);

    Boolean existsByUsername(String username);

}
