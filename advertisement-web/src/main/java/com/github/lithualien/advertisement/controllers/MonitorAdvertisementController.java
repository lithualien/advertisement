package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.MonitorAdvertisementService;
import com.github.lithualien.advertisement.services.MonitorImageService;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementWithImageVO;
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
@RequestMapping("api/monitors/v1")
public class MonitorAdvertisementController {

    private final MonitorAdvertisementService advertisementService;
    private final MonitorImageService monitorImageService;
    private final PagedResourcesAssembler<MonitorAdvertisementWithImageVO> assembler;

    public MonitorAdvertisementController(MonitorAdvertisementService advertisementService,
                                          MonitorImageService monitorImageService, PagedResourcesAssembler<MonitorAdvertisementWithImageVO> assembler) {
        this.advertisementService = advertisementService;
        this.monitorImageService = monitorImageService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<?> all(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "15") Integer size,
                                 @RequestParam(value = "sort", defaultValue = "desc") String sort) {

        Pageable pageable = getPageable(page, size, sort);

        Page<MonitorAdvertisementWithImageVO> advertisements = advertisementService.all(pageable);

        advertisements = isEmptyPage(advertisements, pageable);

        return new ResponseEntity<>(assembler.toModel(advertisements), HttpStatus.OK);
    }

    @GetMapping("/all/{subCategory}")
    public ResponseEntity<?> getAdvertisements(@PathVariable("subCategory") String subCategory,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "15") Integer size,
                                               @RequestParam(value = "sort", defaultValue = "desc") String sort) {

        Pageable pageable = getPageable(page, size, sort);

        Page<MonitorAdvertisementWithImageVO> advertisements
                = advertisementService.findBySubCategory(pageable, subCategory);

        advertisements = isEmptyPage(advertisements, pageable);

        return new ResponseEntity<>(assembler.toModel(advertisements), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonitorAdvertisementWithImageVO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(advertisementService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAdvertisementById(@PathVariable("userId") Long id,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "15") Integer size,
                                                  @RequestParam(value = "sort", defaultValue = "desc") String sort) {

        Pageable pageable = getPageable(page, size, sort);

        Page<MonitorAdvertisementWithImageVO> advertisements
                = advertisementService.findByUserId(pageable, id);

        advertisements = isEmptyPage(advertisements, pageable);

        return new ResponseEntity<>(assembler.toModel(advertisements), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@Valid @RequestBody MonitorAdvertisementSearchVO searchVO,
                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "15") Integer size,
                                    @RequestParam(value = "sort", defaultValue = "desc") String sort) {

        Pageable pageable = getPageable(page, size, sort);

        Page<MonitorAdvertisementWithImageVO> advertisements = advertisementService.findSearch(pageable, searchVO);

        advertisements = isEmptyPage(advertisements, pageable);

        return new ResponseEntity<>(assembler.toModel(advertisements), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MonitorAdvertisementWithImageVO> saveAdvertisement(
            @Valid @RequestBody MonitorAdvertisementVO monitorAdvertisementVO, Authentication authentication) {
        return new ResponseEntity<>(advertisementService.save(monitorAdvertisementVO, authentication.getName()),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MonitorAdvertisementWithImageVO> updateAdvertisement(
            @Valid @RequestBody MonitorAdvertisementVO monitorAdvertisementVO, Authentication authentication) {
        return new ResponseEntity<>(advertisementService.update(monitorAdvertisementVO, authentication.getName()),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("id") Long id, Authentication authentication) {
        advertisementService.delete(id, authentication.getName(), authentication);
    }

    @PostMapping("/images/upload")
    public ResponseEntity<MonitorAdvertisementWithImageVO> uploadImage(
            @RequestParam("advertisement") Long advertisement,
            @RequestParam("images") List<MultipartFile> multipartFiles, Authentication authentication) {

        MonitorAdvertisementWithImageVO advertisementWithImageVO
                = monitorImageService.upload(multipartFiles, advertisement, authentication.getName());

        return new ResponseEntity<>(advertisementWithImageVO, HttpStatus.OK);
    }

    @DeleteMapping("/images/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable("id") Long id, Authentication authentication) {
        monitorImageService.delete(id, authentication.getName());
    }

    private Pageable getPageable(Integer page, Integer size, String sort) {
        String[] array = sort.split(",");

        Sort.Direction sortOrder = "asc".equalsIgnoreCase(array[array.length - 1]) ?
                Sort.Direction.ASC :
                Sort.Direction.DESC;

        return PageRequest.of(page, size, Sort.by(sortOrder,  "id"));
    }

    private Page<MonitorAdvertisementWithImageVO> isEmptyPage(Page<MonitorAdvertisementWithImageVO> advertisements,
                                                              Pageable pageable) {
        if(advertisements == null) {
            return Page.empty(pageable);
        }
        return advertisements;
    }
}
