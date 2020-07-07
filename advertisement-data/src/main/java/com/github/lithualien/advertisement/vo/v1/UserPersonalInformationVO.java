package com.github.lithualien.advertisement.vo.v1;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "name", "email", "number", "city"})
public class UserPersonalInformationVO implements Serializable {

    private Long id;

    @NotBlank(message = "name field is required")
    private String name;

    @NotBlank(message = "email field is required")
    private String email;

    @NotBlank(message = "number field is required")
    private String number;

    @Mapping("this")
    @NotBlank(message = "city field is required")
    private String city;

    @Mapping("this")
    @NotBlank(message = "county field is required")
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