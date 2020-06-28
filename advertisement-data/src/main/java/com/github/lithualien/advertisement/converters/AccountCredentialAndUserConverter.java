package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.Authority;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.repositories.AuthorityRepository;
import com.github.lithualien.advertisement.vo.v1.AccountCredentialVO;

import java.util.HashSet;
import java.util.Set;

public class AccountCredentialAndUserConverter {

    public static User credentialsToUser(AccountCredentialVO accountCredentialVO, AuthorityRepository authorityRepository) {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.findByAuthority("USER"));
        return new User(
                accountCredentialVO.getUsername(),
                accountCredentialVO.getPassword(),
                authorities,
                true,
                true,
                true,
                true
        );
    }
}
