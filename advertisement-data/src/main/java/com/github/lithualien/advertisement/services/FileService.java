package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.ImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<ImageVO> uploadFiles(List<MultipartFile> multipartFiles, Long advertisement, String username);

}
