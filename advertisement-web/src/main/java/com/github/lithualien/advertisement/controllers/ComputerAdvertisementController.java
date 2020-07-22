package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.services.ComputerAdvertisementService;
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
@RequestMapping("api/computers/v1")
public class ComputerAdvertisementController {

    private final ComputerAdvertisementService computerAdvertisementService;
    private final PagedResourcesAssembler<ComputerAdvertisement> assembler;

    public ComputerAdvertisementController(ComputerAdvertisementService computerAdvertisementService, PagedResourcesAssembler<ComputerAdvertisement> assembler) {
        this.computerAdvertisementService = computerAdvertisementService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<?> advertisements(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sort", defaultValue = "desc") String sort
    ) {

        String[] array = sort.split(",");

        Sort.Direction sortOrder = "asc".equalsIgnoreCase(array[array.length - 1]) ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortOrder,  "id"));

        return new ResponseEntity<>(assembler.toModel(computerAdvertisementService.all(pageable)), HttpStatus.OK);
    }
}
