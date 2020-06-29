package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.models.UserPersonalInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserPersonalInformationRepository extends CrudRepository<UserPersonalInformation, Long> {

    @Query("select personalInformation from UserPersonalInformation personalInformation where personalInformation.user = :user")
    UserPersonalInformation findByUser(User user);

}
