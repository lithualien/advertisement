package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.repositories.ComputerAdvertisementRepository;
//import com.github.lithualien.advertisement.vo.v1.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.services.ComputerAdvertisementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ComputerAdvertisementServiceImpl implements ComputerAdvertisementService {

    private final ComputerAdvertisementRepository computerAdvertisementRepository;

    public ComputerAdvertisementServiceImpl(ComputerAdvertisementRepository computerAdvertisementRepository) {
        this.computerAdvertisementRepository = computerAdvertisementRepository;
    }

    @Override
    public Page<ComputerAdvertisement> all(Pageable pageable) {

        return computerAdvertisementRepository.findAll(pageable);
//        return StreamSupport.stream(
//                computerAdvertisementRepository.findAll().spliterator(), false
//        )
//                .collect(Collectors.toSet());
    }

//    @Override
//    public ComputerAdvertisementVO findAdvertisement(String category) {
//        return null;
//    }
//
//    @Override
//    public ComputerAdvertisementVO save(ComputerAdvertisementVO computerAdvertisementVO) {
//        return null;
//    }
//
//    @Override
//    public ComputerAdvertisementVO update(ComputerAdvertisementVO computerAdvertisementVO) {
//        return null;
//    }

    @Override
    public void delete(Long id) {

    }
}
