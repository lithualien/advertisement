package com.github.lithualien.advertisement.vo.v1;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"id", "name", "email", "number", "city"})
public class UserPersonalInformationVO implements Serializable {

    private Long id;
    private String name;

    private String email;
    private String number;
    private CityVO city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPersonalInformationVO)) return false;
        UserPersonalInformationVO that = (UserPersonalInformationVO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
