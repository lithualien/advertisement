package com.github.lithualien.advertisement.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityVO extends BaseVO implements Serializable {

    @NotBlank(message = "city field is required")
    private String city;

}
