package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.ComputerAdvertisementConverter;
import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.repositories.*;
import com.github.lithualien.advertisement.services.ComputerAdvertisementService;
import com.github.lithualien.advertisement.services.UserPersonalInformationService;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ComputerAdvertisementServiceImpl extends AbstractAdvertisementService<ComputerAdvertisement>
        implements ComputerAdvertisementService {

    private final ComputerAdvertisementRepository computerAdvertisementRepository;
    private final SearchRepository searchRepository;

    public ComputerAdvertisementServiceImpl(UserRepository userRepository, CityRepository cityRepository,
                                            SubCategoryRepository subCategoryRepository, TypeRepository typeRepository,
                                            ComputerAdvertisementRepository computerAdvertisementRepository,
                                            UserPersonalInformationService userPersonalInformationService,
                                            ComputerAdvertisementRepository computerAdvertisementRepository1,
                                            SearchRepository searchRepository) {

        super(userRepository, cityRepository, subCategoryRepository, typeRepository,
                computerAdvertisementRepository, userPersonalInformationService);
        this.computerAdvertisementRepository = computerAdvertisementRepository1;
        this.searchRepository = searchRepository;
    }

    @Override
    public Page<ComputerAdvertisementWithImageVO> all(Pageable pageable) {
        return super.abstractAll(pageable)
                .map(e -> {
                    String advertisementUsername = e.getUser().getUsername();
                    UserPersonalInformationVO userPersonalInformationVO =
                            super.getUserPersonalInformation(advertisementUsername);
                    return convertToVOWithImage(e, userPersonalInformationVO);
                });
    }

    @Override
    public ComputerAdvertisementWithImageVO save(ComputerAdvertisementVO computerAdvertisementVO,
                                                         String username) {
        ComputerAdvertisement computerAdvertisement = convertVoToEntity(computerAdvertisementVO, username);
        ComputerAdvertisement savedComputerAdvertisement = super.abstractSave(computerAdvertisement);
        String advertisementUsername = computerAdvertisement.getUser().getUsername();
        UserPersonalInformationVO userPersonalInformationVO = super.getUserPersonalInformation(advertisementUsername);
        return convertToVOWithImage(savedComputerAdvertisement, userPersonalInformationVO);
    }

    @Override
    public ComputerAdvertisementWithImageVO update(ComputerAdvertisementVO computerAdvertisementVO,
                                          String username) {
        ComputerAdvertisement computerAdvertisement = convertVoToEntity(computerAdvertisementVO, username);
        ComputerAdvertisement savedComputerAdvertisement = super.abstractUpdate(computerAdvertisement, username);
        String advertisementUsername = computerAdvertisement.getUser().getUsername();
        UserPersonalInformationVO userPersonalInformationVO = super.getUserPersonalInformation(advertisementUsername);
        return convertToVOWithImage(savedComputerAdvertisement, userPersonalInformationVO);
    }

    @Override
    public void delete(Long id, String username) {
        super.abstractDelete(id, username);
    }

    @Override
    public ComputerAdvertisementWithImageVO findById(Long id) {
        ComputerAdvertisement computerAdvertisement = super.abstractFindById(id);
        String username = computerAdvertisement.getUser().getUsername();
        UserPersonalInformationVO userPersonalInformationVO = super.getUserPersonalInformation(username);
        return convertToVOWithImage(computerAdvertisement, userPersonalInformationVO);
    }

    public Page<ComputerAdvertisementWithImageVO> findByUserId(Pageable pageable, Long id) {
        return getAdvertisementByUserId(pageable, id)
                .map(e -> {
                    String advertisementUsername = e.getUser().getUsername();
                    UserPersonalInformationVO userPersonalInformationVO =
                            super.getUserPersonalInformation(advertisementUsername);
                    return convertToVOWithImage(e, userPersonalInformationVO);
                });
    }

    @Override
    public void isAdvertisementCreator(ComputerAdvertisement computerAdvertisement, String username) {
        super.isAdvertisementCreator(computerAdvertisement, username);
    }

    @Override
    public Page<ComputerAdvertisementWithImageVO> findAllBaseOnSearch(Pageable pageable, String cpu, String gpu,
                                                                      String ram, String memory, String motherboard,
                                                                      String city) {
        return searchRepository
                .findAllBaseOnSearch(pageable, cpu, gpu, ram, memory, motherboard, city)
                .map(advertisement -> {
                    String advertisementUsername = advertisement.getUser().getUsername();
                    UserPersonalInformationVO userPersonalInformationVO =
                            super.getUserPersonalInformation(advertisementUsername);
                    return convertToVOWithImage(advertisement, userPersonalInformationVO);
                });
    }

    private ComputerAdvertisementWithImageVO convertToVOWithImage(ComputerAdvertisement computerAdvertisement,
                                                                  UserPersonalInformationVO userPersonalInformationVO) {
        return ComputerAdvertisementConverter
                .computerAdvertisementToVO(computerAdvertisement, userPersonalInformationVO);
    }

    private ComputerAdvertisement convertVoToEntity(ComputerAdvertisementVO computerVO, String username) {
        return ComputerAdvertisementConverter.computerAdvertisementMultipartFileToEntity(
                computerVO,
                super.getTypeByName(computerVO.getType()),
                super.getCityByName(computerVO.getCity()),
                super.getSubCategoryByName(computerVO.getSubCategory()),
                super.getUserByUsername(username)
        );
    }

    public Page<ComputerAdvertisement> getAdvertisementByUserId(Pageable pageable, Long id) {
        return computerAdvertisementRepository
                .findAllById(pageable, id);
    }
}
