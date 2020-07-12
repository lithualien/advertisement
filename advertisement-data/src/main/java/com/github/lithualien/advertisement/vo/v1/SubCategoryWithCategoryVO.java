package com.github.lithualien.advertisement.vo.v1;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryWithCategoryVO extends SubCategoryVO {

    @Mapping("this")
    @NotBlank(message = "category field is required")
    private String category;
}
