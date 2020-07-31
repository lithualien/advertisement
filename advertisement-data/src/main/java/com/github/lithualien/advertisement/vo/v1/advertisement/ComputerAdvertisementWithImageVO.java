package com.github.lithualien.advertisement.vo.v1.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.lithualien.advertisement.vo.v1.ImageVO;
import com.github.lithualien.advertisement.vo.v1.UserPersonalInformationVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComputerAdvertisementWithImageVO extends ComputerAdvertisementVO {

    private List<ImageVO> images = new ArrayList<>();

    @Mapping("this")
    @JsonProperty("personal_information")
    private UserPersonalInformationVO personalInformationVO;

}
