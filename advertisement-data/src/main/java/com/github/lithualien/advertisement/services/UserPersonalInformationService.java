package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.models.UserPersonalInformation;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;

public interface UserPersonalInformationService {

    UserPersonalInformation getById(Long id);

    UserPersonalInformationVO getUserPersonalInformation(String username);

    UserPersonalInformationVO save(UserPersonalInformationVO userPersonalInformationVO, String username);

    UserPersonalInformationVO update(UserPersonalInformationVO userPersonalInformationVO, String username);

    void delete(Long id);
}
