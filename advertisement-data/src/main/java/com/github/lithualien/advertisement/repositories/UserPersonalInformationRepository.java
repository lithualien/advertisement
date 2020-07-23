package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.models.UserPersonalInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPersonalInformationRepository extends CrudRepository<UserPersonalInformation, Long> {

    @Query("select personalInformation from UserPersonalInformation personalInformation where personalInformation.user = :user")
    Optional<UserPersonalInformation> findByUser(User user);

    @Query("select case when count (personalInformation) > 0 then true else false end " +
            "from UserPersonalInformation personalInformation where personalInformation.user = :user ")
    Boolean findIfPersonalInformationExistsByUser(User user);



}
