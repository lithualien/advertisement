package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.County;
import com.github.lithualien.advertisement.vo.v1.CountyVO;

public class CountyConverter {

    public static County countyVOToCounty(CountyVO countyVO) {
        return new County(
                countyVO.getId(),
                countyVO.getCounty()
        );
    }

    public static CountyVO countyToCountyVO(County county) {
        return new CountyVO(
                county.getId(),
                county.getCounty()
        );
    }

}
