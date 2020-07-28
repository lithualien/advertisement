package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.ComputerAdvertisementService;
import com.github.lithualien.advertisement.services.FileService;
import com.github.lithualien.advertisement.vo.v1.ImageVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementWithImageVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/computers/v1")
public class ComputerAdvertisementController {

    private final ComputerAdvertisementService computerAdvertisementService;
    private final FileService fileService;
    private final PagedResourcesAssembler<ComputerAdvertisementWithImageVO> assembler;

    public ComputerAdvertisementController(ComputerAdvertisementService computerAdvertisementService,
                                           FileService fileService, PagedResourcesAssembler<ComputerAdvertisementWithImageVO> assembler) {
        this.computerAdvertisementService = computerAdvertisementService;
        this.fileService = fileService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<?> getAdvertisements(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sort", defaultValue = "desc") String sort ) {

        String[] array = sort.split(",");

        Sort.Direction sortOrder = "asc".equalsIgnoreCase(array[array.length - 1]) ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortOrder,  "id"));

        return new ResponseEntity<>(assembler.toModel(computerAdvertisementService.all(pageable)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ComputerAdvertisementWithImageVO> saveAdvertisement(
            @Valid @RequestBody ComputerAdvertisementVO computerAdvertisementVO,
            Authentication authentication) {
        return new ResponseEntity<>(computerAdvertisementService.save(computerAdvertisementVO, authentication.getName()),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ComputerAdvertisementVO> updateAdvertisement(
            @Valid @RequestBody ComputerAdvertisementVO computerAdvertisementVO,
            Authentication authentication) {
        return new ResponseEntity<>(computerAdvertisementService.update(computerAdvertisementVO, authentication.getName()),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        computerAdvertisementService.delete(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<List<ImageVO>> uploadImages(
            @RequestParam("advertisement") Long advertisement,
            @RequestParam("images") List<MultipartFile> multipartFiles,
            Authentication authentication) {
        return new ResponseEntity<>(fileService.uploadFiles(multipartFiles, advertisement, authentication.getName()), HttpStatus.OK);
    }
}
