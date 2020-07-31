package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.ComputerAdvertisementConverter;
import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.models.ComputerImage;
import com.github.lithualien.advertisement.repositories.*;
import com.github.lithualien.advertisement.services.ComputerAdvertisementService;
import com.github.lithualien.advertisement.services.FileService;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ComputerAdvertisementServiceImpl extends AbstractAdvertisementService<ComputerAdvertisement> implements ComputerAdvertisementService {

    private final ImageRepository<ComputerImage> computerImageImageRepository;
    public ComputerAdvertisementServiceImpl(UserRepository userRepository, CityRepository cityRepository,
                                            SubCategoryRepository subCategoryRepository, TypeRepository typeRepository,
                                            ComputerAdvertisementRepository computerAdvertisementRepository,
                                            FileService fileService, ImageRepository<ComputerImage> computerImageImageRepository) {

        super(userRepository, cityRepository, subCategoryRepository, typeRepository, computerAdvertisementRepository, fileService);
        this.computerImageImageRepository = computerImageImageRepository;
    }

    @Override
    public Page<ComputerAdvertisementWithImageVO> all(Pageable pageable) {
        return super.abstractAll(pageable)
                .map(this::convertToVOWithImage);
    }

    @Override
    public ComputerAdvertisementWithImageVO save(ComputerAdvertisementVO computerAdvertisementVO,
                                                         String username) {
        ComputerAdvertisement computerAdvertisement = convertVoToEntity(computerAdvertisementVO, username);
        ComputerAdvertisement savedComputerAdvertisement = super.abstractSave(computerAdvertisement);
        return convertToVOWithImage(savedComputerAdvertisement);
    }

    @Override
    public ComputerAdvertisementWithImageVO update(ComputerAdvertisementVO computerAdvertisementVO,
                                          String username) {
        ComputerAdvertisement computerAdvertisement = convertVoToEntity(computerAdvertisementVO, username);
        ComputerAdvertisement savedComputerAdvertisement = super.abstractUpdate(computerAdvertisement, username);
        return convertToVOWithImage(savedComputerAdvertisement);
    }

    @Override
    public void delete(Long id, String username) {
        super.abstractDelete(id, username);
    }

    @Override
    public ComputerAdvertisementWithImageVO findById(Long id) {
        ComputerAdvertisement computerAdvertisement = super.abstractFindById(id);
        return convertToVOWithImage(computerAdvertisement);
    }

    @Override
    public ComputerAdvertisementWithImageVO uploadImages(List<MultipartFile> files, Long id, String username) {
        ComputerAdvertisement advertisement = super.abstractFindById(id);
        List<String> imageUrls = super.abstractUpload(files, advertisement, username);
        saveImages(advertisement, imageUrls);
        return convertToVOWithImage(advertisement);
    }

    private ComputerAdvertisementWithImageVO convertToVOWithImage(ComputerAdvertisement computerAdvertisement) {
        return ComputerAdvertisementConverter.computerAdvertisementToVO(computerAdvertisement);
    }

    private ComputerAdvertisement convertVoToEntity(ComputerAdvertisementVO computerVO, String username) {
        return ComputerAdvertisementConverter.computerAdvertisementMultipartFileToEntity(
                computerVO,
                super.getTypeByName(computerVO.getType()),
                super.getCityByName(computerVO.getCity()),
                super.getSubCategoryByName(computerVO.getSubCategory()),
                super.getUserByUsername(username)
        );
    }

    private void saveImages(ComputerAdvertisement computerAdvertisement, List<String> urls) {
        urls
                .forEach(url -> {
                    ComputerImage image = new ComputerImage();
                    image.setUrl(url);
                    image.setComputerAdvertisement(computerAdvertisement);
                    computerImageImageRepository.save(image);
                });
    }

}
