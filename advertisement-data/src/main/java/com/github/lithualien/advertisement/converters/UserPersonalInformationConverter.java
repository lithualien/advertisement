package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.models.UserPersonalInformation;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;

public class UserPersonalInformationConverter {

    public static UserPersonalInformationVO userPersonalInformationToVO(UserPersonalInformation userPersonalInformation) {
        UserPersonalInformationVO userPersonalInformationVO = DozerConverter.parseObject(userPersonalInformation, UserPersonalInformationVO.class);
        userPersonalInformationVO.setCity(userPersonalInformation.getCity().getCity());
        userPersonalInformationVO.setCounty(userPersonalInformation.getCity().getCounty().getCounty());
        return userPersonalInformationVO;
    }

    public static UserPersonalInformation userPersonalInformationVOToEntity(UserPersonalInformationVO userPersonalInformationVO,
                                                                            City city, User user) {
        UserPersonalInformation userPersonalInformation = DozerConverter.parseObject(
                userPersonalInformationVO, UserPersonalInformation.class
        );
        userPersonalInformation.setCity(city);
        userPersonalInformation.setUser(user);
        return userPersonalInformation;
    }

}
