package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.models.PhoneAdvertisement;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.PhoneAdvertisementSearchVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchRepository {

    Page<ComputerAdvertisement> searchComputers(Pageable pageable, ComputerAdvertisementSearchVO searchVO);

    Page<PhoneAdvertisement> searchPhones(Pageable pageable, PhoneAdvertisementSearchVO searchVO);

}
