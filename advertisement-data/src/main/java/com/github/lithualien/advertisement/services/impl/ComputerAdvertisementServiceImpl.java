package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.ComputerAdvertisementConverter;
import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.repositories.*;
import com.github.lithualien.advertisement.services.ComputerAdvertisementService;
import com.github.lithualien.advertisement.services.UserPersonalInformationService;
import com.github.lithualien.advertisement.services.UserService;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


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
                                            SearchRepository searchRepository, UserService userService) {

        super(userRepository, cityRepository, subCategoryRepository, typeRepository,
                computerAdvertisementRepository, userPersonalInformationService, userService);
        this.computerAdvertisementRepository = computerAdvertisementRepository1;
        this.searchRepository = searchRepository;
    }

    @Override
    public Page<ComputerAdvertisementWithImageVO> all(Pageable pageable) {
        return super.abstractAll(pageable)
                .map(this::convertToVOWithImage);
    }

    @Override
    public Page<ComputerAdvertisementWithImageVO> findBySubCategory(Pageable pageable, String subCategory) {
        SubCategory subCategoryObject = super.getSubCategoryByName(subCategory);
        return computerAdvertisementRepository
                .findAllBySubCategory(pageable, subCategoryObject)
                .map(this::convertToVOWithImage);
    }

    @Override
    public ComputerAdvertisementWithImageVO save(ComputerAdvertisementVO computerAdvertisementVO,
                                                         String username) {
        ComputerAdvertisement computerAdvertisement = convertVoToEntity(computerAdvertisementVO, username);
        ComputerAdvertisement savedComputerAdvertisement = super.abstractSave(computerAdvertisement);
        return convertToVOWithImage(savedComputerAdvertisement);
    }

    @Override
    public ComputerAdvertisementWithImageVO update(ComputerAdvertisementVO computerAdvertisementVO,
                                          String username) {
        ComputerAdvertisement computerAdvertisement = convertVoToEntity(computerAdvertisementVO, username);
        ComputerAdvertisement savedComputerAdvertisement = super.abstractUpdate(computerAdvertisement, username);
        return convertToVOWithImage(savedComputerAdvertisement);
    }

    @Override
    public void delete(Long id, String username, Authentication authentication) {
        super.abstractDelete(id, username, authentication);
    }

    @Override
    public ComputerAdvertisementWithImageVO findById(Long id) {
        ComputerAdvertisement computerAdvertisement = super.abstractFindById(id);
        return convertToVOWithImage(computerAdvertisement);
    }

    @Override
    public Page<ComputerAdvertisementWithImageVO> findByUserId(Pageable pageable, Long id) {

        User user = super.getUserByPersonalInformationId(id);
        return computerAdvertisementRepository.findAllByUser(pageable, user)
                .map(this::convertToVOWithImage);
    }

    @Override
    public void isAdvertisementCreator(ComputerAdvertisement computerAdvertisement, String username) {
        super.isAdvertisementCreator(computerAdvertisement, username);
    }

    @Override
    public Page<ComputerAdvertisementWithImageVO> findAllBaseOnSearch(Pageable pageable,
                                                                      ComputerAdvertisementSearchVO searchVO) {
        return searchRepository
                .searchComputers(pageable, searchVO)
                .map(this::convertToVOWithImage);
    }

    private ComputerAdvertisementWithImageVO convertToVOWithImage(ComputerAdvertisement computerAdvertisement) {
        String advertisementUsername = computerAdvertisement.getUser().getUsername();
        UserPersonalInformationVO userPersonalInformationVO =
                super.getUserPersonalInformation(advertisementUsername);
        return ComputerAdvertisementConverter
                .computerAdvertisementToVO(computerAdvertisement, userPersonalInformationVO);
    }

    private ComputerAdvertisement convertVoToEntity(ComputerAdvertisementVO computerVO, String username) {
        return ComputerAdvertisementConverter.voToEntity(
                computerVO,
                super.getTypeByName(computerVO.getType()),
                super.getCityByName(computerVO.getCity()),
                super.getSubCategoryByName(computerVO.getSubCategory()),
                super.getUserByUsername(username)
        );
    }
}
