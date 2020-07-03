package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.UserPersonalInformationService;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("api/users/information/v1")
@Validated
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
    public UserPersonalInformationVO saveUserPersonalInformation(@Valid @RequestBody UserPersonalInformationVO userPersonalInformationVO,
                                                                 Authentication authentication) {
        return userPersonalInformationService.save(userPersonalInformationVO, authentication.getName());
    }

    @PutMapping
    public UserPersonalInformationVO updateUserPersonalInformation(@Valid @RequestBody UserPersonalInformationVO userPersonalInformationVO,
                                                                   Authentication authentication) {
        return userPersonalInformationService.update(userPersonalInformationVO, authentication.getName());
    }


    @DeleteMapping("/{id}")
    public void deleteUserPersonalInformation(@PathVariable("id") @Min(1) Long id) {
        userPersonalInformationService.delete(id);
    }

}
