package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.*;
import com.github.lithualien.advertisement.vo.v1.ImageVO;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementWithImageVO;

import java.util.ArrayList;
import java.util.List;

public class MonitorAdvertisementConverter {

    public static MonitorAdvertisementWithImageVO entityToVO(MonitorAdvertisement advertisement,
                                                             UserPersonalInformationVO userPersonalInformationVO) {
        MonitorAdvertisementWithImageVO advertisementVO
                = DozerConverter.parseObject(advertisement, MonitorAdvertisementWithImageVO.class);

        advertisementVO.setType(advertisement.getType().getType());
        advertisementVO.setCity(advertisement.getCity().getCity());
        advertisementVO.setSubCategory(advertisement.getSubCategory().getSubCategory());
        advertisementVO.setUserPersonalInformationVO(userPersonalInformationVO);

        List<ImageVO> images = getImageList(advertisement);

        advertisementVO.setImages(images);

        return advertisementVO;
    }

    public static MonitorAdvertisement voToEntity(MonitorAdvertisementVO advertisementVO, Type type, City city,
                                                  SubCategory subCategory, User user) {
        MonitorAdvertisement advertisement = DozerConverter.parseObject(advertisementVO, MonitorAdvertisement.class);
        advertisement.setType(type);
        advertisement.setCity(city);
        advertisement.setSubCategory(subCategory);
        advertisement.setUser(user);

        return advertisement;
    }

    private static List<ImageVO> getImageList(MonitorAdvertisement advertisement) {
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
