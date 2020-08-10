package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.*;
import com.github.lithualien.advertisement.vo.v1.ImageVO;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ExternalDeviceAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ExternalDeviceAdvertisementWithImageVO;

import java.util.ArrayList;
import java.util.List;

public class ExternalDeviceAdvertisementConverter {

    public static ExternalDeviceAdvertisementWithImageVO entityToVo(ExternalDeviceAdvertisement advertisement,
                                                              UserPersonalInformationVO userPersonalInformationVO) {
        ExternalDeviceAdvertisementWithImageVO advertisementVO
                = DozerConverter.parseObject(advertisement, ExternalDeviceAdvertisementWithImageVO.class);

        advertisementVO.setType(advertisement.getType().getType());
        advertisementVO.setCity(advertisement.getCity().getCity());
        advertisementVO.setSubCategory(advertisement.getSubCategory().getSubCategory());
        advertisementVO.setUserPersonalInformationVO(userPersonalInformationVO);

        List<ImageVO> images = getImageList(advertisement);

        advertisementVO.setImages(images);

        return advertisementVO;
    }

    public static ExternalDeviceAdvertisement voToEntity(ExternalDeviceAdvertisementVO advertisementVO, Type type,
                                                         City city, SubCategory subCategory, User user) {
        ExternalDeviceAdvertisement advertisement
                = DozerConverter.parseObject(advertisementVO, ExternalDeviceAdvertisement.class);

        advertisement.setType(type);
        advertisement.setCity(city);
        advertisement.setSubCategory(subCategory);
        advertisement.setUser(user);

        return advertisement;
    }

    private static List<ImageVO> getImageList(ExternalDeviceAdvertisement advertisement) {
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
