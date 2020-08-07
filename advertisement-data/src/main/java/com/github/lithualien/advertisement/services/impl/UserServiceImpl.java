package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.AccountCredentialAndUserConverter;
import com.github.lithualien.advertisement.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.repositories.AuthorityRepository;
import com.github.lithualien.advertisement.repositories.UserRepository;
import com.github.lithualien.advertisement.services.UserService;
import com.github.lithualien.advertisement.vo.v1.AccountCredentialVO;
import com.github.lithualien.advertisement.vo.v1.AdminVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthorityRepository authorityRepository;

    @Value("${basic.auth.code}")
    private String basicAuthCode;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository
                .findUserByUsername(s)
                .<ResourceNotFoundException> orElseThrow( () -> {
                    throw new ResourceNotFoundException("User with specified username does not exist.");
                });
    }

    @Override
    public void registerUser(AccountCredentialVO accountCredentialVO) {
        if(userRepository.existsByUsername(accountCredentialVO.getUsername())) {
            throw new ResourceAlreadyExistsException("User is already registered.");
        }

        User user = AccountCredentialAndUserConverter.credentialsToUser(accountCredentialVO, authorityRepository);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<String> userLoginToken(AccountCredentialVO accountCredentialVO, String host) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", basicAuthCode);
        HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
        String authURL = "http://"+ host + "/oauth/token?grant_type=password&username=" + accountCredentialVO.getUsername() + "&password=" + accountCredentialVO.getPassword();
        return restTemplate.postForEntity(authURL, entity, String.class);
    }

    @Override
    public AdminVO isAdmin(Authentication authentication) {
        AdminVO adminVO = new AdminVO();
        adminVO.setIsAdmin(false);

        authentication
                .getAuthorities()
                .forEach(authority -> {
                    if(authority.getAuthority().contains("ADMIN")) {
                        adminVO.setIsAdmin(true);
                    }
                });

        return adminVO;
    }
}
