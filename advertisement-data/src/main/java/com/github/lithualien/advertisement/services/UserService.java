package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.AccountCredentialVO;
import com.github.lithualien.advertisement.vo.v1.AdminVO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserService {

    void registerUser(AccountCredentialVO accountCredentialVO);

    ResponseEntity<String> userLoginToken(AccountCredentialVO accountCredentialVO, String host);

    AdminVO isAdmin(Authentication authentication);

}
