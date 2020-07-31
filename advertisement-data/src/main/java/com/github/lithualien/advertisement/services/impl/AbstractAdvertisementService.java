package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.exceptions.NotContentCreatorException;
import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.models.Type;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.models.superclass.Advertisement;
import com.github.lithualien.advertisement.repositories.*;
import com.github.lithualien.advertisement.services.FileService;
import com.github.lithualien.advertisement.services.UserPersonalInformationService;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public abstract class AbstractAdvertisementService<T extends Advertisement> {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final TypeRepository typeRepository;
    private final AdvertisementRepository<T> advertisementRepository;
    private final FileService fileService;
    private final UserPersonalInformationService userPersonalInformationService;

    protected AbstractAdvertisementService(UserRepository userRepository, CityRepository cityRepository,
                                           SubCategoryRepository subCategoryRepository, TypeRepository typeRepository,
                                           AdvertisementRepository<T> advertisementRepository, FileService fileService, UserPersonalInformationService userPersonalInformationService) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.typeRepository = typeRepository;
        this.advertisementRepository = advertisementRepository;
        this.fileService = fileService;
        this.userPersonalInformationService = userPersonalInformationService;
    }

    public Page<T> abstractAll(Pageable pageable) {
        return advertisementRepository
                .findAll(pageable);
    }

    public T abstractFindById(Long id) {
        return getAdvertisementById(id);
    }

    public T abstractSave(T object) {
        object.setId(null);
        return advertisementRepository.save(object);
    }

    public T abstractUpdate(T object, String username) {
        isIdPresent(object);
        T advertisement = getAdvertisementById(object.getId());
        isAdvertisementCreator(advertisement, username);
        return advertisementRepository.save(object);
    }

    public void abstractDelete(Long id, String username) {
        T advertisement = getAdvertisementById(id);
        isAdvertisementCreator(advertisement, username);
        advertisementRepository.delete(advertisement);
    }

    public List<String> abstractUpload(List<MultipartFile> files, T advertisement, String username){
        isAdvertisementCreator(advertisement, username);
        return fileService.uploadFiles(files);
    }

    protected User getUserByUsername(String username) {
        return userRepository
                .findUserByUsername(username)
                .<ResourceNotFoundException> orElseThrow( () -> {
                    throw new ResourceNotFoundException("User with specified username does not exist.");
                });
    }

    protected City getCityByName(String city) {
        return cityRepository
                .findByCity(city)
                .<ResourceNotFoundException> orElseThrow( () -> {
                    throw new ResourceNotFoundException("Specified city in county was not found.");
                });
    }

    protected SubCategory getSubCategoryByName(String subCategory) {
        return subCategoryRepository
                .findBySubCategory(subCategory)
                .<ResourceNotFoundException> orElseThrow( () -> {
                    throw new ResourceNotFoundException("Specified sub category does not exists.");
                });
    }

    protected Type getTypeByName(String type) {
        return typeRepository
                .findByType(type)
                .<ResourceNotFoundException> orElseThrow( () -> {
            throw new ResourceNotFoundException("Specified type does not exists.");
        });
    }
    protected T getAdvertisementById(Long id) {
        return advertisementRepository
                .findById(id)
                .<ResourceNotFoundException> orElseThrow( ()-> {
                    throw new ResourceNotFoundException("Advertisement with id=" + id + " was not found.");
                });
    }

    protected UserPersonalInformationVO getUserPersonalInformation(String username) {
        try {
            return userPersonalInformationService.getUserPersonalInformation(username);
        } catch (ResourceNotFoundException e) {
            return null;
        }
    }

    private void isAdvertisementCreator(T object, String username) {
        if(!object.getUser().getUsername()
                .equals(username)) {
            throw new NotContentCreatorException("User did not create the advertisement.");
        }
    }

    private void isIdPresent(T object) {
        if(object.getId() == null) {
            throw new ResourceNotFoundException("Please fill the id field.");
        }
    }
}
