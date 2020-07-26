package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.*;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementWithImageVO;
import com.github.lithualien.advertisement.vo.v1.ImageVO;

import java.util.ArrayList;
import java.util.List;

public class ComputerAdvertisementConverter {

    public static ComputerAdvertisementWithImageVO computerAdvertisementToVO(ComputerAdvertisement computerAdvertisement) {
        ComputerAdvertisementWithImageVO computerAdvertisementVO = DozerConverter.parseObject(computerAdvertisement, ComputerAdvertisementWithImageVO.class);
        computerAdvertisementVO.setType(computerAdvertisement.getType().getType());
        computerAdvertisementVO.setCity(computerAdvertisement.getCity().getCity());
        computerAdvertisementVO.setSubCategory(computerAdvertisement.getSubCategory().getSubCategory());

        List<ImageVO> urls = new ArrayList<>();

        computerAdvertisement
                .getImages()
                .forEach(e -> urls.add(new ImageVO(e.getUrl())));

        computerAdvertisementVO.setImages(urls);
        return computerAdvertisementVO;
    }

    public static ComputerAdvertisement computerAdvertisementMultipartFileToEntity(ComputerAdvertisementVO computerAdvertisementVO,
                                                                                   Type type, City city, SubCategory subCategory,
                                                                                   User user) {
        ComputerAdvertisement computerAdvertisement = DozerConverter.parseObject(computerAdvertisementVO, ComputerAdvertisement.class);
        computerAdvertisement.setId(null);
        computerAdvertisement.setType(type);
        computerAdvertisement.setCity(city);
        computerAdvertisement.setSubCategory(subCategory);
        computerAdvertisement.setUser(user);
        return computerAdvertisement;
    }
}
