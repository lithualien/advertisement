package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.models.UserPersonalInformation;
import com.github.lithualien.advertisement.repositories.CityRepository;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;

public class UserPersonalInformationConverter {

    public static UserPersonalInformationVO userPersonalInformationToVO(UserPersonalInformation userPersonalInformation) {
        UserPersonalInformationVO userPersonalInformationVO = DozerConverter.parseObject(userPersonalInformation, UserPersonalInformationVO.class);
        userPersonalInformationVO.setCity(userPersonalInformation.getCity().getCity());
        userPersonalInformationVO.setCounty(userPersonalInformation.getCity().getCounty().getCounty());
        return userPersonalInformationVO;
    }

    public static UserPersonalInformation userPersonalInformationVOToEntity(UserPersonalInformationVO userPersonalInformationVO,
                                                                            CityRepository cityRepository, User user) {
        UserPersonalInformation userPersonalInformation = DozerConverter.parseObject(
                userPersonalInformationVO, UserPersonalInformation.class
        );
        userPersonalInformation.setCity(cityRepository.findByCity(userPersonalInformationVO.getCity()));
        userPersonalInformation.setUser(user);
        return userPersonalInformation;
    }

}
