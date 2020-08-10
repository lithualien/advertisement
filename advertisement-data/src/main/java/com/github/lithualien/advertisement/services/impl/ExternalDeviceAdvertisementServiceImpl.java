package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.ExternalDeviceAdvertisementConverter;
import com.github.lithualien.advertisement.models.ExternalDeviceAdvertisement;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.repositories.*;
import com.github.lithualien.advertisement.services.ExternalDeviceAdvertisementService;
import com.github.lithualien.advertisement.services.UserPersonalInformationService;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ExternalDeviceAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ExternalDeviceAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ExternalDeviceAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExternalDeviceAdvertisementServiceImpl
       extends AbstractAdvertisementService<ExternalDeviceAdvertisement> implements ExternalDeviceAdvertisementService {

    private final SearchRepository searchRepository;
    private final ExternalDeviceAdvertisementRepository externalDeviceRepository;

    public ExternalDeviceAdvertisementServiceImpl(UserRepository userRepository, CityRepository cityRepository,
                                                  SubCategoryRepository subCategoryRepository,
                                                  TypeRepository typeRepository, SearchRepository searchRepository,
                                                  ExternalDeviceAdvertisementRepository externalDeviceRepository,
                                                  UserPersonalInformationService userPersonalInformationService) {

        super(userRepository, cityRepository, subCategoryRepository, typeRepository, externalDeviceRepository,
                userPersonalInformationService);

        this.searchRepository = searchRepository;
        this.externalDeviceRepository = externalDeviceRepository;
    }

    @Override
    public Page<ExternalDeviceAdvertisementWithImageVO> all(Pageable pageable) {
        return super
                .abstractAll(pageable)
                .map(this::convertToVOWithImage);
    }

    @Override
    public Page<ExternalDeviceAdvertisementWithImageVO> findBySubCategory(Pageable pageable, String subCategory) {
        return null;
    }

    @Override
    public ExternalDeviceAdvertisementWithImageVO findById(Long id) {
        ExternalDeviceAdvertisement externalDeviceAdvertisement = super.abstractFindById(id);
        return this.convertToVOWithImage(externalDeviceAdvertisement);
    }

    @Override
    public ExternalDeviceAdvertisement findEntityById(Long id) {
        return super.abstractFindById(id);
    }

    @Override
    public Page<ExternalDeviceAdvertisementWithImageVO> findByUserId(Pageable pageable, Long id) {
        User user = super.getUserById(id);
        return externalDeviceRepository
                .findAllByUser(pageable, user)
                .map(this::convertToVOWithImage);
    }

    // todo implement search
    @Override
    public Page<ExternalDeviceAdvertisementWithImageVO> findSearch(Pageable pageable,
                                                                   ExternalDeviceAdvertisementSearchVO searchVO) {

        return null;
    }

    @Override
    public ExternalDeviceAdvertisementWithImageVO save(ExternalDeviceAdvertisementVO advertisementVO, String username) {
        ExternalDeviceAdvertisement advertisement = convertVoToEntity(advertisementVO, username);
        ExternalDeviceAdvertisement savedAdvertisement = super.abstractSave(advertisement);
        return this.convertToVOWithImage(savedAdvertisement);
    }

    @Override
    public ExternalDeviceAdvertisementWithImageVO update(ExternalDeviceAdvertisementVO advertisementVO, String username) {
        ExternalDeviceAdvertisement advertisement = convertVoToEntity(advertisementVO, username);
        ExternalDeviceAdvertisement updatedAdvertisement = super.abstractUpdate(advertisement, username);
        return this.convertToVOWithImage(updatedAdvertisement);
    }

    @Override
    public void delete(Long id, String username) {
        super.abstractDelete(id, username);
    }

    @Override
    public void isAdvertisement(ExternalDeviceAdvertisement advertisement, String username) {
        super.isAdvertisementCreator(advertisement, username);
    }

    private ExternalDeviceAdvertisementWithImageVO convertToVOWithImage(ExternalDeviceAdvertisement advertisement) {
        String advertisementUsername = advertisement.getUser().getUsername();
        UserPersonalInformationVO userPersonalInformationVO = super.getUserPersonalInformation(advertisementUsername);
        return ExternalDeviceAdvertisementConverter.entityToVo(advertisement, userPersonalInformationVO);
    }

    private ExternalDeviceAdvertisement convertVoToEntity(ExternalDeviceAdvertisementVO advertisementVO, String user) {
        return ExternalDeviceAdvertisementConverter.voToEntity(
                advertisementVO,
                super.getTypeByName(advertisementVO.getType()),
                super.getCityByName(advertisementVO.getCity()),
                super.getSubCategoryByName(advertisementVO.getSubCategory()),
                super.getUserByUsername(user)
        );
    }
}
