package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.MonitorAdvertisementConverter;
import com.github.lithualien.advertisement.models.MonitorAdvertisement;
import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.repositories.*;
import com.github.lithualien.advertisement.services.MonitorAdvertisementService;
import com.github.lithualien.advertisement.services.UserPersonalInformationService;
import com.github.lithualien.advertisement.services.UserService;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class MonitorServiceImpl extends AbstractAdvertisementService<MonitorAdvertisement> implements MonitorAdvertisementService {

    private final SearchRepository searchRepository;
    private final MonitorAdvertisementRepository monitorAdvertisementRepository;

    public MonitorServiceImpl(UserRepository userRepository, CityRepository cityRepository, TypeRepository typeRepository,
                              SearchRepository searchRepository, SubCategoryRepository subcategoryRepository,
                              UserPersonalInformationService userPersonalInformationService,
                              MonitorAdvertisementRepository monitorAdvertisementRepository, UserService userService) {
        super(userRepository, cityRepository, subcategoryRepository, typeRepository, monitorAdvertisementRepository,
                userPersonalInformationService, userService);

        this.searchRepository = searchRepository;
        this.monitorAdvertisementRepository = monitorAdvertisementRepository;
    }

    @Override
    public Page<MonitorAdvertisementWithImageVO> all(Pageable pageable) {
        return super
                .abstractAll(pageable)
                .map(this::convertToVOWithImage);
    }

    @Override
    public Page<MonitorAdvertisementWithImageVO> findBySubCategory(Pageable pageable, String subCategory) {
        SubCategory subCategory1 = super.getSubCategoryByName(subCategory);
        return monitorAdvertisementRepository
                .findAllBySubCategory(pageable, subCategory1)
                .map(this::convertToVOWithImage);
    }

    @Override
    public MonitorAdvertisementWithImageVO findById(Long id) {
        MonitorAdvertisement advertisement = super.getAdvertisementById(id);
        return convertToVOWithImage(advertisement);
    }

    @Override
    public MonitorAdvertisement findEntityById(Long id) {
        return super.getAdvertisementById(id);
    }

    @Override
    public Page<MonitorAdvertisementWithImageVO> findByUserId(Pageable pageable, Long id) {
        User user = super.getUserByPersonalInformationId(id);
        return monitorAdvertisementRepository
                .findAllByUser(pageable, user)
                .map(this::convertToVOWithImage);
    }

    @Override
    public Page<MonitorAdvertisementWithImageVO> findSearch(Pageable pageable, MonitorAdvertisementSearchVO searchVO) {
        return searchRepository
                .searchMonitor(pageable, searchVO)
                .map(this::convertToVOWithImage);
    }

    @Override
    public MonitorAdvertisementWithImageVO save(MonitorAdvertisementVO advertisementVO, String username) {
        MonitorAdvertisement advertisement = convertVoToEntity(advertisementVO, username);
        MonitorAdvertisement savedAdvertisement = super.abstractSave(advertisement);
        return this.convertToVOWithImage(savedAdvertisement);
    }

    @Override
    public MonitorAdvertisementWithImageVO update(MonitorAdvertisementVO advertisementVO, String username) {
        MonitorAdvertisement advertisement = convertVoToEntity(advertisementVO, username);
        MonitorAdvertisement updatedAdvertisement = super.abstractSave(advertisement);
        return this.convertToVOWithImage(updatedAdvertisement);
    }

    @Override
    public void delete(Long id, String username, Authentication authentication) {
        super.abstractDelete(id, username, authentication);
    }

    @Override
    public void isAdvertisementCreator(MonitorAdvertisement advertisement, String username) {
        super.isAdvertisementCreator(advertisement, username);
    }

    private MonitorAdvertisementWithImageVO convertToVOWithImage(MonitorAdvertisement advertisement) {
        String advertisementUsername = advertisement.getUser().getUsername();
        UserPersonalInformationVO userPersonalInformationVO = super.getUserPersonalInformation(advertisementUsername);

        return MonitorAdvertisementConverter.entityToVO(advertisement, userPersonalInformationVO);
    }

    private MonitorAdvertisement convertVoToEntity(MonitorAdvertisementVO advertisementVO, String username) {
        return MonitorAdvertisementConverter.voToEntity(
                advertisementVO,
                super.getTypeByName(advertisementVO.getType()),
                super.getCityByName(advertisementVO.getCity()),
                super.getSubCategoryByName(advertisementVO.getSubCategory()),
                super.getUserByUsername(username)
        );
    }

}
