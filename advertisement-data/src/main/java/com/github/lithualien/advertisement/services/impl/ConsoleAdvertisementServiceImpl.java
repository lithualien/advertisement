package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.ConsoleAdvertisementConverter;
import com.github.lithualien.advertisement.models.ConsoleAdvertisement;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.repositories.*;
import com.github.lithualien.advertisement.services.ConsoleAdvertisementService;
import com.github.lithualien.advertisement.services.UserPersonalInformationService;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ConsoleAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ConsoleAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ConsoleAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConsoleAdvertisementServiceImpl extends AbstractAdvertisementService<ConsoleAdvertisement>
        implements ConsoleAdvertisementService {

    private final SearchRepository searchRepository;
    private final ConsoleAdvertisementRepository consoleAdvertisementRepository;

    public ConsoleAdvertisementServiceImpl(UserRepository userRepository, CityRepository cityRepository,
                                           SubCategoryRepository subCategoryRepository, TypeRepository typeRepository,
                                           UserPersonalInformationService userPersonalInformationService,
                                           ConsoleAdvertisementRepository consoleAdvertisementRepository,
                                           SearchRepository searchRepository,
                                           ConsoleAdvertisementRepository consoleAdvertisementRepository1) {
        super(userRepository, cityRepository, subCategoryRepository, typeRepository, consoleAdvertisementRepository,
                userPersonalInformationService);

        this.searchRepository = searchRepository;
        this.consoleAdvertisementRepository = consoleAdvertisementRepository1;
    }

    @Override
    public Page<ConsoleAdvertisementWithImageVO> all(Pageable pageable, String subCategory) {
        return super
                .abstractAll(pageable)
                .map(this::convertToVOWithImage);
    }

    @Override
    public ConsoleAdvertisementWithImageVO findById(Long id) {
        ConsoleAdvertisement advertisement = super.abstractFindById(id);
        return convertToVOWithImage(advertisement);
    }

    @Override
    public ConsoleAdvertisement findEntityById(Long id) {
        return super.getAdvertisementById(id);
    }

    @Override
    public Page<ConsoleAdvertisementWithImageVO> findByUserId(Pageable pageable, Long id, String subCategory) {
        User user = super.getUserById(id);
        return consoleAdvertisementRepository
                .findAllByUser(pageable, user)
                .map(this::convertToVOWithImage);
    }

    // todo implement search.

    @Override
    public Page<ConsoleAdvertisementWithImageVO> findSearch(Pageable pageable, ConsoleAdvertisementSearchVO searchVO) {
        return null;
    }


    @Override
    public ConsoleAdvertisementWithImageVO save(ConsoleAdvertisementVO consoleAdvertisementVO, String username) {
        ConsoleAdvertisement consoleAdvertisement = this.convertVoToEntity(consoleAdvertisementVO, username);
        ConsoleAdvertisement savedConsoleAdvertisement = super.abstractSave(consoleAdvertisement);
        return this.convertToVOWithImage(savedConsoleAdvertisement);
    }

    @Override
    public ConsoleAdvertisementWithImageVO update(ConsoleAdvertisementVO consoleAdvertisementVO, String username) {
        ConsoleAdvertisement consoleAdvertisement = this.convertVoToEntity(consoleAdvertisementVO, username);
        ConsoleAdvertisement updatedConsoleAdvertisement = super.abstractUpdate(consoleAdvertisement, username);
        return this.convertToVOWithImage(updatedConsoleAdvertisement);
    }

    @Override
    public void delete(Long id, String username) {
        super.abstractDelete(id, username);
    }

    @Override
    public void isAdvertisementCreator(ConsoleAdvertisement consoleAdvertisement, String username) {
        super.isAdvertisementCreator(consoleAdvertisement, username);
    }

    private ConsoleAdvertisementWithImageVO convertToVOWithImage(ConsoleAdvertisement advertisement) {
        String advertisementUsername = advertisement.getUser().getUsername();
        UserPersonalInformationVO userPersonalInformationVO = super.getUserPersonalInformation(advertisementUsername);

        return ConsoleAdvertisementConverter.entityToVO(advertisement, userPersonalInformationVO);
    }

    private ConsoleAdvertisement convertVoToEntity(ConsoleAdvertisementVO advertisementVO, String username) {
        return ConsoleAdvertisementConverter.voToEntity(
                advertisementVO,
                super.getTypeByName(advertisementVO.getType()),
                super.getCityByName(advertisementVO.getCity()),
                super.getSubCategoryByName(advertisementVO.getSubCategory()),
                super.getUserByUsername(username)
        );
    }

}
