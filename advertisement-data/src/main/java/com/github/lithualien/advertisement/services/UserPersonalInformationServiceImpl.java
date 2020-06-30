package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.converters.UserPersonalInformationConverter;
import com.github.lithualien.advertisement.exceptions.NotContentCreatorException;
import com.github.lithualien.advertisement.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.models.UserPersonalInformation;
import com.github.lithualien.advertisement.repositories.CityRepository;
import com.github.lithualien.advertisement.repositories.UserPersonalInformationRepository;
import com.github.lithualien.advertisement.repositories.UserRepository;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import org.springframework.stereotype.Service;

@Service
public class UserPersonalInformationServiceImpl implements UserPersonalInformationService {

    private final UserPersonalInformationRepository userPersonalInformationRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public UserPersonalInformationServiceImpl(UserPersonalInformationRepository userPersonalInformationRepository, UserRepository userRepository, CityRepository cityRepository) {
        this.userPersonalInformationRepository = userPersonalInformationRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public UserPersonalInformationVO getUserPersonalInformation(String username) {
        User user = userRepository.findUserByUsername(username);

        UserPersonalInformation userPersonalInformation = getUserPersonalInformation(user);

        return UserPersonalInformationConverter.userPersonalInformationToVO(userPersonalInformation);
    }

    @Override
    public UserPersonalInformationVO save(UserPersonalInformationVO userPersonalInformationVO, String username) {
        User user = userRepository.findUserByUsername(username);

        if(userPersonalInformationRepository.findIfPersonalInformationExistsByUser(user)) {
            throw new ResourceAlreadyExistsException("User has already added personal information.");
        }

        userPersonalInformationVO.setId(null);

        UserPersonalInformation userPersonalInformation = userPersonalInformationRepository.save(UserPersonalInformationConverter
                .userPersonalInformationVOToEntity(userPersonalInformationVO, cityRepository, user));

        return UserPersonalInformationConverter.userPersonalInformationToVO(userPersonalInformation);
    }

    @Override
    public UserPersonalInformationVO update(UserPersonalInformationVO userPersonalInformationVO, String username) {
        UserPersonalInformation userPersonalInformation = userPersonalInformationRepository
                .findById(userPersonalInformationVO.getId())
                .<ResourceNotFoundException>orElseThrow( () -> {
                    throw new ResourceNotFoundException("User personal information was not found.");
                });

        if(!username.equals(userPersonalInformation.getUser().getUsername())) {
            throw new NotContentCreatorException("Insufficient permissions.");
        }
        User user = userPersonalInformation.getUser();

        userPersonalInformation = userPersonalInformationRepository.save(UserPersonalInformationConverter.userPersonalInformationVOToEntity(userPersonalInformationVO,
                cityRepository, user));

        return UserPersonalInformationConverter.userPersonalInformationToVO(userPersonalInformation);
    }

    @Override
    public void delete(UserPersonalInformationVO userPersonalInformationVO) {
//        userPersonalInformationRepository.delete();
    }

    private UserPersonalInformation getUserPersonalInformation(User user) {
        return userPersonalInformationRepository
                .findByUser(user)
                .<ResourceNotFoundException>orElseThrow( () -> {
                    throw new ResourceNotFoundException("User has not added personal information.");
                });
    }


}
