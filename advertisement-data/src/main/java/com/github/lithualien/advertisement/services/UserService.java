package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.AccountCredentialVO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    void registerUser(AccountCredentialVO accountCredentialVO);

    ResponseEntity<String> getUserToken(AccountCredentialVO accountCredentialVO, String host);

}
