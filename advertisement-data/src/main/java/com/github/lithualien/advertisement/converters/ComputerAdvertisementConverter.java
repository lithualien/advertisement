package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.*;
import com.github.lithualien.advertisement.vo.v1.ImageVO;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementWithImageVO;

import java.util.ArrayList;
import java.util.List;

public class ComputerAdvertisementConverter {

    public static ComputerAdvertisementWithImageVO computerAdvertisementToVO(ComputerAdvertisement computerAdvertisement,
                                                                 UserPersonalInformationVO userPersonalInformationVO) {

        ComputerAdvertisementWithImageVO computerAdvertisementVO
                = DozerConverter.parseObject(computerAdvertisement, ComputerAdvertisementWithImageVO.class);

        computerAdvertisementVO.setType(computerAdvertisement.getType().getType());
        computerAdvertisementVO.setCity(computerAdvertisement.getCity().getCity());
        computerAdvertisementVO.setSubCategory(computerAdvertisement.getSubCategory().getSubCategory());
        computerAdvertisementVO.setPersonalInformationVO(userPersonalInformationVO);

        List<ImageVO> urls = getImageList(computerAdvertisement);

        computerAdvertisementVO.setImages(urls);

        return computerAdvertisementVO;
    }

    public static ComputerAdvertisement voToEntity(ComputerAdvertisementVO computerAdvertisementVO, Type type, City city,
                                                   SubCategory subCategory, User user) {

        ComputerAdvertisement computerAdvertisement
                = DozerConverter.parseObject(computerAdvertisementVO, ComputerAdvertisement.class);
        computerAdvertisement.setType(type);
        computerAdvertisement.setCity(city);
        computerAdvertisement.setSubCategory(subCategory);
        computerAdvertisement.setUser(user);
        return computerAdvertisement;
    }

    private static List<ImageVO> getImageList(ComputerAdvertisement computerAdvertisement) {
        List<ImageVO> urls = new ArrayList<>();

        computerAdvertisement
                .getImages()
                .forEach(e -> {
                    ImageVO imageVO = new ImageVO();
                    imageVO.setId(e.getId());
                    imageVO.setUrl(e.getUrl());
                    urls.add(imageVO);
                });
        return urls;
    }
}
