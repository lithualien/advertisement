package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.UserService;
import com.github.lithualien.advertisement.vo.v1.AccountCredentialVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/user/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AccountCredentialVO accountCredentialVO, HttpServletRequest httpServletRequest) {
        userService.registerUser(accountCredentialVO);
        return userService.getUserToken(accountCredentialVO, httpServletRequest.getHeader("host"));
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody AccountCredentialVO accountCredentialVO, HttpServletRequest httpServletRequest) {
        return userService.getUserToken(accountCredentialVO, httpServletRequest.getHeader("host"));
    }
}

