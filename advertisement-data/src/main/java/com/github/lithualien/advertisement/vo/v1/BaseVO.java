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
public abstract class BaseVO implements Serializable {

    protected Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseVO baseVO = (BaseVO) o;
        return id.equals(baseVO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
