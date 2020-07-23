package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/file/v1")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public void uploadFile(
            @RequestParam("files") List<MultipartFile> multipartFiles,
            @RequestParam("adv_id") Long advertisementId,
            Authentication authentication) {
        fileService.uploadFile(multipartFiles, advertisementId, authentication.getName());
    }

}
