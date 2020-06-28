package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface AuthorityRepository extends Repository<Authority, Long> {

    @Query("select authority from Authority authority where authority.authority = :authority")
    Authority findByAuthority(String authority);

}
