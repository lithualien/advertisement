package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.*;
import com.github.lithualien.advertisement.vo.v1.ImageVO;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.PhoneAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.PhoneAdvertisementWithImageVO;
import java.util.ArrayList;
import java.util.List;

public class PhoneAdvertisementConverter {

    public static PhoneAdvertisementWithImageVO entityToVO(PhoneAdvertisement phoneAdvertisement,
                                                           UserPersonalInformationVO userPersonalInformationVO) {
        PhoneAdvertisementWithImageVO phoneAdvertisementWithImageVO =
                DozerConverter.parseObject(phoneAdvertisement, PhoneAdvertisementWithImageVO.class);

        phoneAdvertisementWithImageVO.setType(phoneAdvertisement.getType().getType());
        phoneAdvertisementWithImageVO.setCity(phoneAdvertisement.getCity().getCity());
        phoneAdvertisementWithImageVO.setSubCategory(phoneAdvertisement.getSubCategory().getSubCategory());
        phoneAdvertisementWithImageVO.setPersonalInformationVO(userPersonalInformationVO);

        List<ImageVO> urls = getImageList(phoneAdvertisement);

        phoneAdvertisementWithImageVO.setImages(urls);

        return phoneAdvertisementWithImageVO;
    }

    public static PhoneAdvertisement voToEntity(PhoneAdvertisementVO phoneAdvertisementVO, Type type, City city,
                                                SubCategory subCategory, User user) {
        PhoneAdvertisement phoneAdvertisement
                = DozerConverter.parseObject(phoneAdvertisementVO, PhoneAdvertisement.class);

        phoneAdvertisement.setType(type);
        phoneAdvertisement.setCity(city);
        phoneAdvertisement.setSubCategory(subCategory);
        phoneAdvertisement.setUser(user);

        return phoneAdvertisement;
    }

    private static List<ImageVO> getImageList(PhoneAdvertisement phoneAdvertisement) {
        List<ImageVO> urls = new ArrayList<>();

        phoneAdvertisement
                .getPhoneImages()
                .forEach(advertisement -> {
                    ImageVO imageVO = new ImageVO();
                    imageVO.setId(advertisement.getId());
                    imageVO.setUrl(advertisement.getUrl());
                    urls.add(imageVO);
                });
        return urls;
    }

}
