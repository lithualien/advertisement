package com.github.lithualien.advertisement.vo.v1.advertisement;

import com.github.lithualien.advertisement.vo.v1.ImageVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComputerAdvertisementWithImageVO extends ComputerAdvertisementVO {

    private List<ImageVO> images = new ArrayList<>();

}
