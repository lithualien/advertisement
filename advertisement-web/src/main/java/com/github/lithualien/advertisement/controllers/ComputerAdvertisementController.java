package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.services.ComputerAdvertisementService;
import com.github.lithualien.advertisement.vo.v1.AdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.ComputerAdvertisementWithImageVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("api/computers/v1")
public class ComputerAdvertisementController {

    private final ComputerAdvertisementService computerAdvertisementService;
    private final PagedResourcesAssembler<ComputerAdvertisementWithImageVO> assembler;

    public ComputerAdvertisementController(ComputerAdvertisementService computerAdvertisementService,
                                           PagedResourcesAssembler<ComputerAdvertisementWithImageVO> assembler) {
        this.computerAdvertisementService = computerAdvertisementService;
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
    public ResponseEntity<ComputerAdvertisementVO> saveAdvertisement(
            @Valid @RequestBody ComputerAdvertisementVO computerAdvertisementVO) {
        return new ResponseEntity<>(computerAdvertisementService.save(computerAdvertisementVO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ComputerAdvertisementVO> updateAdvertisement(
            @Valid @RequestBody ComputerAdvertisementVO computerAdvertisementVO) {
        return new ResponseEntity<>(computerAdvertisementService.update(computerAdvertisementVO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        computerAdvertisementService.delete(id);
    }
}
