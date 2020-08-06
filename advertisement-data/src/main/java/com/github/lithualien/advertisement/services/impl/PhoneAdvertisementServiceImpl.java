package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.PhoneAdvertisementConverter;
import com.github.lithualien.advertisement.models.PhoneAdvertisement;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.repositories.*;
import com.github.lithualien.advertisement.services.PhoneAdvertisementService;
import com.github.lithualien.advertisement.services.UserPersonalInformationService;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PhoneAdvertisementServiceImpl extends AbstractAdvertisementService<PhoneAdvertisement> implements PhoneAdvertisementService {

    private final SearchRepository searchRepository;
    private final PhoneAdvertisementRepository repository;

    public PhoneAdvertisementServiceImpl(UserRepository userRepository, CityRepository cityRepository,
                                         SubCategoryRepository subCategoryRepository, TypeRepository typeRepository,
                                         PhoneAdvertisementRepository phoneAdvertisementRepository,
                                         UserPersonalInformationService userPersonalInformationService,
                                         SearchRepository searchRepository, PhoneAdvertisementRepository repository) {

        super(userRepository, cityRepository, subCategoryRepository, typeRepository,
                phoneAdvertisementRepository, userPersonalInformationService);
        this.searchRepository = searchRepository;
        this.repository = repository;
    }

    @Override
    public Page<PhoneAdvertisementWithImageVO> all(Pageable pageable) {
        return super
                .abstractAll(pageable)
                .map(this::convertToVOWithImage);
    }

    @Override
    public PhoneAdvertisementWithImageVO findById(Long id) {
        PhoneAdvertisement advertisement = super.getAdvertisementById(id);
        return convertToVOWithImage(advertisement);
    }

    @Override
    public PhoneAdvertisement findEntityById(Long id) {
        return super.abstractFindById(id);
    }

    @Override
    public Page<PhoneAdvertisementWithImageVO> findByUserId(Pageable pageable, Long id) {
        User user = super.getUserById(id);
        return repository.findAllByUser(pageable, user)
                .map(this::convertToVOWithImage);
    }

    @Override
    public Page<PhoneAdvertisementWithImageVO> findSearch(Pageable pageable, PhoneAdvertisementSearchVO searchVO) {
        return searchRepository
                .searchPhones(pageable, searchVO)
                .map(this::convertToVOWithImage);
    }

    @Override
    public PhoneAdvertisementWithImageVO save(PhoneAdvertisementVO phoneAdvertisementVO, String username) {
        PhoneAdvertisement phoneAdvertisement = convertVoToEntity(phoneAdvertisementVO, username);
        PhoneAdvertisement savedPhoneAdvertisement = super.abstractSave(phoneAdvertisement);
        return convertToVOWithImage(savedPhoneAdvertisement);
    }

    @Override
    public PhoneAdvertisementWithImageVO update(PhoneAdvertisementVO phoneAdvertisementVO, String username) {
        PhoneAdvertisement phoneAdvertisement = convertVoToEntity(phoneAdvertisementVO, username);
        PhoneAdvertisement updatedPhoneAdvertisement = super.abstractUpdate(phoneAdvertisement, username);
        return convertToVOWithImage(updatedPhoneAdvertisement);
    }

    @Override
    public void delete(Long id, String username) {
        super.abstractDelete(id, username);
    }

    @Override
    public void isAdvertisementCreator(PhoneAdvertisement phoneAdvertisement, String username) {
        super.isAdvertisementCreator(phoneAdvertisement, username);
    }

    private PhoneAdvertisementWithImageVO convertToVOWithImage(PhoneAdvertisement phoneAdvertisement) {
        String advertisementUsername = phoneAdvertisement.getUser().getUsername();
        UserPersonalInformationVO userPersonalInformationVO = super.getUserPersonalInformation(advertisementUsername);

        return PhoneAdvertisementConverter
                .entityToVO(phoneAdvertisement, userPersonalInformationVO);
    }

    public PhoneAdvertisement convertVoToEntity(PhoneAdvertisementVO phoneAdvertisementVO, String username) {
        return PhoneAdvertisementConverter.voToEntity(
                phoneAdvertisementVO,
                super.getTypeByName(phoneAdvertisementVO.getType()),
                super.getCityByName(phoneAdvertisementVO.getCity()),
                super.getSubCategoryByName(phoneAdvertisementVO.getSubCategory()),
                super.getUserByUsername(username)
        );
    }
}
