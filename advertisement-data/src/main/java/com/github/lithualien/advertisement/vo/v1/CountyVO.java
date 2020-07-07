package com.github.lithualien.advertisement.vo.v1;

import com.github.lithualien.advertisement.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountyVO extends BaseEntity {

    @NotBlank(message = "county field is required")
    private String county;

    public CountyVO(Long id, String county)  {
        super(id);
        this.county = county;
    }

}
