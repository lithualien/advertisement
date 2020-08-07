package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.models.Authority;
import com.github.lithualien.advertisement.services.UserService;
import com.github.lithualien.advertisement.vo.v1.AccountCredentialVO;
import com.github.lithualien.advertisement.vo.v1.AdminVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api/users/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AccountCredentialVO accountCredentialVO, HttpServletRequest httpServletRequest) {
        userService.registerUser(accountCredentialVO);
        return userService.userLoginToken(accountCredentialVO, httpServletRequest.getHeader("host"));
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@Valid @RequestBody AccountCredentialVO accountCredentialVO, HttpServletRequest httpServletRequest) {
        return userService.userLoginToken(accountCredentialVO, httpServletRequest.getHeader("host"));
    }

    @GetMapping("/admin")
    public ResponseEntity<AdminVO> isAdmin(Authentication authentication) {
        return new ResponseEntity<>(userService.isAdmin(authentication), HttpStatus.OK);
    }
}

