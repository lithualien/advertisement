package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.PhoneAdvertisementService;
import com.github.lithualien.advertisement.services.PhoneImageService;
import com.github.lithualien.advertisement.vo.v1.advertisement.PhoneAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.PhoneAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.PhoneAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
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
@RequestMapping("api/phones/v1")
public class PhoneAdvertisementController {

    private final PhoneAdvertisementService phoneAdvertisementService;
    private final PagedResourcesAssembler<PhoneAdvertisementWithImageVO> assembler;
    private final PhoneImageService phoneImageService;

    public PhoneAdvertisementController(PhoneAdvertisementService phoneAdvertisementService,
                                        PagedResourcesAssembler<PhoneAdvertisementWithImageVO> assembler,
                                        PhoneImageService phoneImageService) {
        this.phoneAdvertisementService = phoneAdvertisementService;
        this.assembler = assembler;
        this.phoneImageService = phoneImageService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestParam(value = "sort", defaultValue = "desc") String sort) {
            Pageable pageable = getPageable(page, size, sort);

            return new ResponseEntity<>(assembler.toModel(phoneAdvertisementService.all(pageable)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneAdvertisementWithImageVO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(phoneAdvertisementService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findByUserId(
            @PathVariable("id") Long id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestParam(value = "sort", defaultValue = "desc") String sort) {
        Pageable pageable = getPageable(page, size, sort);
        Page<PhoneAdvertisementWithImageVO> advertisements = phoneAdvertisementService.findByUserId(pageable, id);
        return new ResponseEntity<>(assembler.toModel(advertisements), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<?> findSearch(
            @Valid @RequestBody PhoneAdvertisementSearchVO phoneAdvertisementSearchVO,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestParam(value = "sort", defaultValue = "desc") String sort) {
        Pageable pageable = getPageable(page, size, sort);
        Page<PhoneAdvertisementWithImageVO> advertisements
                = phoneAdvertisementService.findSearch(pageable, phoneAdvertisementSearchVO);
        return new ResponseEntity<>(assembler.toModel(advertisements), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PhoneAdvertisementVO> save(@Valid @RequestBody PhoneAdvertisementVO phoneAdvertisementVO,
                                                     Authentication authentication) {
        PhoneAdvertisementVO savedAdvertisementVO
                = phoneAdvertisementService.save(phoneAdvertisementVO, authentication.getName());

        return new ResponseEntity<>(savedAdvertisementVO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PhoneAdvertisementVO> update(@Valid @RequestBody PhoneAdvertisementVO phoneAdvertisementVO,
                                                     Authentication authentication) {
        PhoneAdvertisementVO updatedAdvertisementVO
                = phoneAdvertisementService.update(phoneAdvertisementVO, authentication.getName());

        return new ResponseEntity<>(updatedAdvertisementVO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id, Authentication authentication) {
        phoneAdvertisementService.delete(id, authentication.getName());
    }

    @PostMapping("/images/upload")
    public ResponseEntity<PhoneAdvertisementWithImageVO> uploadImages(
            @RequestParam("advertisement") Long advertisement,
            @RequestParam("images") List<MultipartFile> multipartFiles,
            Authentication authentication) {

        PhoneAdvertisementWithImageVO advertisementVO
                = phoneImageService.upload(multipartFiles, advertisement, authentication.getName());
        return new ResponseEntity<>(advertisementVO, HttpStatus.OK);
    }

    @DeleteMapping("/images/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable("id") Long id, Authentication authentication) {
        phoneAdvertisementService.delete(id, authentication.getName());
    }

    private Pageable getPageable(Integer page, Integer size, String sort) {
        String[] array = sort.split(",");

        Sort.Direction sortOrder = "asc".equalsIgnoreCase(array[array.length - 1]) ? Sort.Direction.ASC : Sort.Direction.DESC;

        return PageRequest.of(page, size, Sort.by(sortOrder,  "id"));
    }

}
