package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.models.Type;
import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.models.superclass.Advertisement;
import com.github.lithualien.advertisement.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class AbstractAdvertisementService<T extends Advertisement> {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final TypeRepository typeRepository;

    protected AbstractAdvertisementService(UserRepository userRepository, CityRepository cityRepository,
                                           SubCategoryRepository subCategoryRepository, TypeRepository typeRepository) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.typeRepository = typeRepository;
    }

    public Page<T> all(Pageable pageable, AdvertisementRepository<T> advertisementRepository) {
        return advertisementRepository
                .findAll(pageable);
    }

    public T save(AdvertisementRepository<T> advertisementRepository, Advertisement advertisement) {
        return null;
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
}
