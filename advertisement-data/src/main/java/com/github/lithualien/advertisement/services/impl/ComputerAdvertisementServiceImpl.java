package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.ComputerAdvertisementConverter;
import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.repositories.*;
import com.github.lithualien.advertisement.services.FileService;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.services.ComputerAdvertisementService;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComputerAdvertisementServiceImpl extends AbstractAdvertisementService<ComputerAdvertisement> implements ComputerAdvertisementService {

    private final ComputerAdvertisementRepository computerAdvertisementRepository;
    private final FileService fileService;


    public ComputerAdvertisementServiceImpl(ComputerAdvertisementRepository computerAdvertisementRepository, UserRepository userRepository,
                                            FileService fileService, CityRepository cityRepository,
                                            SubCategoryRepository subCategoryRepository, TypeRepository typeRepository) {
        super(userRepository, cityRepository, subCategoryRepository, typeRepository);
        this.computerAdvertisementRepository = computerAdvertisementRepository;
        this.fileService = fileService;
    }

    @Override
    public Page<ComputerAdvertisementWithImageVO> all(Pageable pageable) {
        return super
                .all(pageable, computerAdvertisementRepository)
                .map(this::convertToVOWithImage);
    }

    @Override
    public ComputerAdvertisementWithImageVO save(ComputerAdvertisementVO computerAdvertisementVO,
                                                         String username) {
        ComputerAdvertisement computerAdvertisement = convertVoWithMultipartFileToEntity(computerAdvertisementVO, username);
        ComputerAdvertisement savedComputerAdvertisement = computerAdvertisementRepository.save(computerAdvertisement);
        return convertToVOWithImage(savedComputerAdvertisement);
    }

    @Override
    public ComputerAdvertisementVO update(ComputerAdvertisementVO computerAdvertisementVO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private ComputerAdvertisementWithImageVO convertToVOWithImage(ComputerAdvertisement computerAdvertisement) {
        return ComputerAdvertisementConverter.computerAdvertisementToVO(computerAdvertisement);
    }

    private ComputerAdvertisement convertVoWithMultipartFileToEntity(ComputerAdvertisementVO computerVO, String username) {
        return ComputerAdvertisementConverter.computerAdvertisementMultipartFileToEntity(
                computerVO,
                super.getTypeByName(computerVO.getType()),
                super.getCityByName(computerVO.getCity()),
                super.getSubCategoryByName(computerVO.getSubCategory()),
                super.getUserByUsername(username)
        );
    }

}
