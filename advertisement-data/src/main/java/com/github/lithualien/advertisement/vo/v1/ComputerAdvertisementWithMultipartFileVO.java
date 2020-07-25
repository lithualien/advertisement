package com.github.lithualien.advertisement.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComputerAdvertisementWithMultipartFileVO extends ComputerAdvertisementVO {

    @NotBlank(message = "images field is required")
    private List<MultipartFile> images = new ArrayList<>();

}
