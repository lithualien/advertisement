package com.github.lithualien.advertisement.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageVO extends BaseVO {

    private Set<URL> urls;
}
