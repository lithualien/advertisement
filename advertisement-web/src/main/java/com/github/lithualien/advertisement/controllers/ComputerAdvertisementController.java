package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.ComputerAdvertisementService;
import com.github.lithualien.advertisement.services.ComputerImageService;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementWithImageVO;
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
@RequestMapping("api/computers/v1")
public class ComputerAdvertisementController {

    private final ComputerAdvertisementService advertisementService;
    private final ComputerImageService computerImageService;
    private final PagedResourcesAssembler<ComputerAdvertisementWithImageVO> assembler;

    public ComputerAdvertisementController(ComputerAdvertisementService advertisementService,
                                           ComputerImageService computerImageService,
                                           PagedResourcesAssembler<ComputerAdvertisementWithImageVO> assembler) {
        this.advertisementService = advertisementService;
        this.computerImageService = computerImageService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<?> all(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "15") Integer size,
                                 @RequestParam(value = "sort", defaultValue = "desc") String sort) {

        Pageable pageable = getPageable(page, size, sort);

        Page<ComputerAdvertisementWithImageVO> advertisements = advertisementService.all(pageable);

        advertisements = isEmptyPage(advertisements, pageable);

        return new ResponseEntity<>(assembler.toModel(advertisements), HttpStatus.OK);
    }

    @GetMapping("/all/{subCategory}")
    public ResponseEntity<?> getBySubCategory(@PathVariable("subCategory") String subCategory,
                                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", defaultValue = "15") Integer size,
                                              @RequestParam(value = "sort", defaultValue = "desc") String sort) {

        Pageable pageable = getPageable(page, size, sort);

        Page<ComputerAdvertisementWithImageVO> advertisements
                = advertisementService.findBySubCategory(pageable, subCategory);

        advertisements = isEmptyPage(advertisements, pageable);

        return new ResponseEntity<>(assembler.toModel(advertisements), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ComputerAdvertisementWithImageVO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(advertisementService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAdvertisementById(@PathVariable("id") Long id,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "15") Integer size,
                                                  @RequestParam(value = "sort", defaultValue = "desc") String sort) {
        Pageable pageable = getPageable(page, size, sort);

        Page<ComputerAdvertisementWithImageVO> advertisements = advertisementService.findByUserId(pageable, id);

        advertisements = isEmptyPage(advertisements, pageable);

        return new ResponseEntity<>(assembler.toModel(advertisements), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@Valid @RequestBody ComputerAdvertisementSearchVO searchVO,
                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "15") Integer size,
                                    @RequestParam(value = "sort", defaultValue = "desc") String sort) {

        Pageable pageable = getPageable(page, size, sort);

        Page<ComputerAdvertisementWithImageVO> advertisements
                = advertisementService.findAllBaseOnSearch(pageable, searchVO);

        advertisements = isEmptyPage(advertisements, pageable);

        return new ResponseEntity<>(assembler.toModel(advertisements), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ComputerAdvertisementWithImageVO> saveAdvertisement(
            @Valid @RequestBody ComputerAdvertisementVO computerAdvertisementVO, Authentication authentication) {
        return new ResponseEntity<>(advertisementService.save(computerAdvertisementVO, authentication.getName()),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ComputerAdvertisementVO> updateAdvertisement(
            @Valid @RequestBody ComputerAdvertisementVO computerAdvertisementVO, Authentication authentication) {
        return new ResponseEntity<>(advertisementService.update(computerAdvertisementVO, authentication.getName()),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id, Authentication authentication) {
        advertisementService.delete(id, authentication.getName(), authentication);
    }

    @PostMapping("/images/upload")
    public ResponseEntity<ComputerAdvertisementVO> uploadImages(@RequestParam("advertisement") Long advertisement,
                                                            @RequestParam("images") List<MultipartFile> multipartFiles,
                                                            Authentication authentication) {

        ComputerAdvertisementWithImageVO advertisementWithImageVO =
                computerImageService.upload(multipartFiles, advertisement, authentication.getName());

        return new ResponseEntity<>(advertisementWithImageVO, HttpStatus.OK);
    }

    @DeleteMapping("/images/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable("id") Long id, Authentication authentication) {
        computerImageService.delete(id, authentication.getName());
    }

    private Pageable getPageable(Integer page, Integer size, String sort) {
        String[] array = sort.split(",");

        Sort.Direction sortOrder = "asc".equalsIgnoreCase(array[array.length - 1]) ?
                Sort.Direction.ASC :
                Sort.Direction.DESC;

        return PageRequest.of(page, size, Sort.by(sortOrder,  "id"));
    }

    private Page<ComputerAdvertisementWithImageVO> isEmptyPage(Page<ComputerAdvertisementWithImageVO> advertisements,
                                                              Pageable pageable) {
        if(advertisements == null) {
            return Page.empty(pageable);
        }
        return advertisements;
    }
}
