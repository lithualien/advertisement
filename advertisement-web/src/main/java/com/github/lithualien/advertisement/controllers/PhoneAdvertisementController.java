package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.PhoneAdvertisementService;
import com.github.lithualien.advertisement.vo.v1.advertisement.PhoneAdvertisementWithImageVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/phones/v1")
public class PhoneAdvertisementController {

    private final PhoneAdvertisementService phoneAdvertisementService;
    private final PagedResourcesAssembler<PhoneAdvertisementWithImageVO> assembler;

    public PhoneAdvertisementController(PhoneAdvertisementService phoneAdvertisementService, PagedResourcesAssembler<PhoneAdvertisementWithImageVO> assembler) {
        this.phoneAdvertisementService = phoneAdvertisementService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestParam(value = "sort", defaultValue = "desc") String sort) {
            Pageable pageable = getPageable(page, size, sort);

            return new ResponseEntity<>(assembler.toModel(phoneAdvertisementService.all(pageable)), HttpStatus.OK);

    }

    private Pageable getPageable(Integer page, Integer size, String sort) {
        String[] array = sort.split(",");

        Sort.Direction sortOrder = "asc".equalsIgnoreCase(array[array.length - 1]) ? Sort.Direction.ASC : Sort.Direction.DESC;

        return PageRequest.of(page, size, Sort.by(sortOrder,  "id"));
    }

}
