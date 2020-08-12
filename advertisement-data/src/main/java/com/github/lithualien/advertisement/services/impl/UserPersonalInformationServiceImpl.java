package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.UserPersonalInformationConverter;
import com.github.lithualien.advertisement.exceptions.NotContentCreatorException;
import com.github.lithualien.advertisement.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.models.County;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.models.UserPersonalInformation;
import com.github.lithualien.advertisement.repositories.CityRepository;
import com.github.lithualien.advertisement.repositories.CountyRepository;
import com.github.lithualien.advertisement.repositories.UserPersonalInformationRepository;
import com.github.lithualien.advertisement.repositories.UserRepository;
import com.github.lithualien.advertisement.services.UserPersonalInformationService;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import org.springframework.stereotype.Service;

@Service
public class UserPersonalInformationServiceImpl implements UserPersonalInformationService {

    private final UserPersonalInformationRepository userPersonalInformationRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final CountyRepository countyRepository;

    public UserPersonalInformationServiceImpl(UserPersonalInformationRepository userPersonalInformationRepository, UserRepository userRepository, CityRepository cityRepository, CountyRepository countyRepository) {
        this.userPersonalInformationRepository = userPersonalInformationRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.countyRepository = countyRepository;
    }

    @Override
    public UserPersonalInformation getById(Long id) {
        return getUserPersonalInformationById(id);
    }

    @Override
    public UserPersonalInformationVO getUserPersonalInformation(String username) {
        User user = getUser(username);

        UserPersonalInformation userPersonalInformation = getUserPersonalInformationByUser(user);

        return UserPersonalInformationConverter.userPersonalInformationToVO(userPersonalInformation);
    }

    @Override
    public UserPersonalInformationVO save(UserPersonalInformationVO userPersonalInformationVO, String username) {
        User user = getUser(username);

        if(userPersonalInformationRepository.findIfPersonalInformationExistsByUser(user)) {
            throw new ResourceAlreadyExistsException("User has already added personal information.");
        }

        County county = getCounty(userPersonalInformationVO.getCounty());

        City city = getCity(userPersonalInformationVO.getCity(), county);

        userPersonalInformationVO.setId(null);

        UserPersonalInformation userPersonalInformation = userPersonalInformationRepository.save(UserPersonalInformationConverter
                .userPersonalInformationVOToEntity(userPersonalInformationVO, city, user));

        return UserPersonalInformationConverter.userPersonalInformationToVO(userPersonalInformation);
    }

    @Override
    public UserPersonalInformationVO update(UserPersonalInformationVO userPersonalInformationVO, String username) {
        UserPersonalInformation userPersonalInformation = getUserPersonalInformationById(userPersonalInformationVO.getId());

        if(!username.equals(userPersonalInformation.getUser().getUsername())) {
            throw new NotContentCreatorException("Insufficient permissions.");
        }

        County county = getCounty(userPersonalInformationVO.getCounty());

        City city = getCity(userPersonalInformationVO.getCity(), county);

        User user = userPersonalInformation.getUser();

        userPersonalInformation = userPersonalInformationRepository.save(UserPersonalInformationConverter.userPersonalInformationVOToEntity(userPersonalInformationVO,
                city, user));

        return UserPersonalInformationConverter.userPersonalInformationToVO(userPersonalInformation);
    }

    @Override
    public void delete(Long id) {
        UserPersonalInformation userPersonalInformation = userPersonalInformationRepository
                .findById(id)
                .<ResourceNotFoundException> orElseThrow( () -> {
                    throw new ResourceNotFoundException("User personal information with id=" + id + " was not found.");
                });
        userPersonalInformationRepository.delete(userPersonalInformation);
    }

    private UserPersonalInformation getUserPersonalInformationByUser(User user) {
        return userPersonalInformationRepository
                .findByUser(user)
                .<ResourceNotFoundException>orElseThrow( () -> {
                    throw new ResourceNotFoundException("User has not added personal information.");
                });
    }

    private County getCounty(String county) {
        return countyRepository
                .findByName(county)
                .<ResourceNotFoundException>orElseThrow(() -> {
                    throw new ResourceNotFoundException("Specified county was not found.");
                });
    }

    private City getCity(String city, County county) {
        return cityRepository
                .findByCityAndCounty(city, county)
                .<ResourceNotFoundException> orElseThrow( () -> {
                    throw new ResourceNotFoundException("Specified city in county was not found.");
                });
    }

    private UserPersonalInformation getUserPersonalInformationById(Long id) {
        return userPersonalInformationRepository
                .findById(id)
                .<ResourceNotFoundException>orElseThrow( () -> {
                    throw new ResourceNotFoundException("User personal information was not found.");
                });
    }

    private User getUser(String username) {
        return userRepository
                .findUserByUsername(username)
                .<ResourceNotFoundException> orElseThrow( () -> {
                    throw new ResourceNotFoundException("User with specified username does not exist.");
                });
    }

}
