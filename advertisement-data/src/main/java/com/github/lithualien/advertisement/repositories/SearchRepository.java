package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchRepository {

    Page<ComputerAdvertisement> findAllBaseOnSearch(Pageable pageable, String cpu, String gpu, String ram,
                                                    String memory, String motherboard, String city);

}
