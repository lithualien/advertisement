package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.*;
import com.github.lithualien.advertisement.vo.v1.ImageVO;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ConsoleAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ConsoleAdvertisementWithImageVO;

import java.util.ArrayList;
import java.util.List;

public class ConsoleAdvertisementConverter {

    public static ConsoleAdvertisementWithImageVO entityToVO(ConsoleAdvertisement advertisement,
                                                             UserPersonalInformationVO userPersonalInformationVO) {
        ConsoleAdvertisementWithImageVO advertisementVO
                = DozerConverter.parseObject(advertisement, ConsoleAdvertisementWithImageVO.class);

        advertisementVO.setType(advertisement.getType().getType());
        advertisementVO.setCity(advertisement.getCity().getCity());
        advertisementVO.setSubCategory(advertisement.getSubCategory().getSubCategory());
        advertisementVO.setUserPersonalInformationVO(userPersonalInformationVO);

        List<ImageVO> images = getImageList(advertisement);

        advertisementVO.setImages(images);

        return advertisementVO;
    }

    public static ConsoleAdvertisement voToEntity(ConsoleAdvertisementVO advertisementVO, Type type, City city,
                                                  SubCategory subCategory, User user) {

        ConsoleAdvertisement advertisement = DozerConverter.parseObject(advertisementVO, ConsoleAdvertisement.class);
        advertisement.setType(type);
        advertisement.setCity(city);
        advertisement.setSubCategory(subCategory);
        advertisement.setUser(user);

        return advertisement;
    }

    private static List<ImageVO> getImageList(ConsoleAdvertisement advertisement) {
        List<ImageVO> urls = new ArrayList<>();

        advertisement
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
