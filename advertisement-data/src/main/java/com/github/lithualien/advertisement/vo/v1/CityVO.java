package com.github.lithualien.advertisement.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityVO implements Serializable {

    private Long id;
    private String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityVO)) return false;
        CityVO cityVO = (CityVO) o;
        return Objects.equals(id, cityVO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
