package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.superclass.Image;
import com.github.lithualien.advertisement.repositories.ImageRepository;
import com.github.lithualien.advertisement.services.FileService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public abstract class AbstractImageService<T extends Image> {

    private final ImageRepository<T> imageRepository;
    private final FileService fileService;

    protected AbstractImageService(ImageRepository<T> imageRepository, FileService fileService) {
        this.imageRepository = imageRepository;
        this.fileService = fileService;
    }

    protected void abstractSave(T image) {
        imageRepository.save(image);
    }

    public List<String> abstractUpload(List<MultipartFile> files) {
        return fileService.uploadFiles(files);
    }

    protected void abstractDelete(T image) {
        imageRepository.delete(image);
    }

    protected T abstractGetImage(Long id) {
        return imageRepository.findById(id)
                .<ResourceNotFoundException>orElseThrow( () -> {
                    throw new ResourceNotFoundException("Image with id=" + id + " was not found.");
                });
    }


}
