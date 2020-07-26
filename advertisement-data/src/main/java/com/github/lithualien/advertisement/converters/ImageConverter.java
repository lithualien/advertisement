package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.ComputerImage;
import com.github.lithualien.advertisement.models.superclass.Image;
import com.github.lithualien.advertisement.vo.v1.ImageVO;

import java.util.ArrayList;
import java.util.List;

public class ImageConverter {

    public static List<ComputerImage> stringToComputerImage(List<String> urls) {
        List<ComputerImage> images = new ArrayList<>();
        urls
                .forEach(e -> {
                    ComputerImage image = new ComputerImage();
                    image.setUrl(e);
                    images.add(image);
                });
        return images;
    }

    public static List<ComputerImage> stringToImageVO (List<String> urls) {
        List<ComputerImage> images = new ArrayList<>();
        urls
                .forEach(e -> {
                    ComputerImage image = new ComputerImage();
                    image.setUrl(e);
                    images.add(image);
                });
        return images;
    }

    public static ImageVO entityToImageVO(ComputerImage image) {
        ImageVO imageVO = new ImageVO();
        imageVO.setUrl(image.getUrl());
        return imageVO;
    }
}
