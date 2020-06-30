package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.models.UserPersonalInformation;
import com.github.lithualien.advertisement.services.UserPersonalInformationService;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/users/information/v1")
public class PersonalInformationController {

    private final UserPersonalInformationService userPersonalInformationService;

    public PersonalInformationController(UserPersonalInformationService userPersonalInformationService) {
        this.userPersonalInformationService = userPersonalInformationService;
    }

    @GetMapping
    public UserPersonalInformationVO getPersonalInformation(Authentication authentication) {
        return userPersonalInformationService.getUserPersonalInformation(authentication.getName());
    }

    @PostMapping
    public UserPersonalInformationVO saveUserPersonalInformation(@RequestBody UserPersonalInformationVO userPersonalInformationVO,
                                                                 Authentication authentication) {
        return userPersonalInformationService.save(userPersonalInformationVO, authentication.getName());
    }

    @PutMapping
    public UserPersonalInformationVO updateUserPersonalInformation(@RequestBody UserPersonalInformationVO userPersonalInformationVO,
                                                                   Authentication authentication) {
        return userPersonalInformationService.update(userPersonalInformationVO, authentication.getName());
    }


    @DeleteMapping
    public void deleteUserPersonalInformation(@RequestBody UserPersonalInformationVO userPersonalInformationVO) {
        userPersonalInformationService.delete(userPersonalInformationVO);
    }

}
