package com.github.lithualien.advertisement.vo.v1;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

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

    @Mapping("this")
    private String city;

    @Mapping("this")
    private String county;

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
