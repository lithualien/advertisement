package com.github.lithualien.advertisement.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<String> uploadFiles(List<MultipartFile> multipartFiles);

}
