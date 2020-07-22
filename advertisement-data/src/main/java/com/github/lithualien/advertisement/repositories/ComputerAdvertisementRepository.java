package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ComputerAdvertisementRepository extends CrudRepository<ComputerAdvertisement, Long> {

    Page<ComputerAdvertisement> findAll(Pageable pageable);

}
