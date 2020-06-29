package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.converters.DozerConverter;
import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.models.UserPersonalInformation;
import com.github.lithualien.advertisement.repositories.CityRepository;
import com.github.lithualien.advertisement.repositories.UserPersonalInformationRepository;
import com.github.lithualien.advertisement.repositories.UserRepository;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import org.springframework.stereotype.Service;

@Service
public class UserPersonalInformationServiceImpl implements UserPersonalInformationService {

    private final UserPersonalInformationRepository userPersonalInformationRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public UserPersonalInformationServiceImpl(UserPersonalInformationRepository userPersonalInformationRepository, UserRepository userRepository, CityRepository cityRepository) {
        this.userPersonalInformationRepository = userPersonalInformationRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public UserPersonalInformationVO getUserPersonalInformation(String username) {
        User user = userRepository.findUserByUsername(username);
        UserPersonalInformation userPersonalInformation = userPersonalInformationRepository.findByUser(user);
        return DozerConverter.parseObject(userPersonalInformation, UserPersonalInformationVO.class);
    }

    @Override
    public UserPersonalInformationVO save(UserPersonalInformationVO userPersonalInformationVO, String username) {
        UserPersonalInformation userPersonalInformation = DozerConverter.parseObject(
                userPersonalInformationVO, UserPersonalInformation.class
        );
        userPersonalInformation.setUser(userRepository.findUserByUsername(username));
        userPersonalInformation.setCity(cityRepository.findByCity(userPersonalInformationVO.getCity().getCity()));
        userPersonalInformationRepository.save(userPersonalInformation);
        return userPersonalInformationVO;
    }
}
