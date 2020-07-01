package com.github.lithualien.advertisement.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountyVO {

    private Long id;
    private String county;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountyVO)) return false;
        CountyVO countyVO = (CountyVO) o;
        return Objects.equals(id, countyVO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
