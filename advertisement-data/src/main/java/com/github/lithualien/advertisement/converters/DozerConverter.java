package com.github.lithualien.advertisement.converters;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.HashSet;
import java.util.Set;

public class DozerConverter {

    private static final Mapper mapper = new DozerBeanMapper();


    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> Set<D> parseSet(Set<O> origin, Class<D> destination) {
        Set<D> destinationObject = new HashSet<>();
        origin
                .forEach(e -> destinationObject.add(mapper.map(e, destination)));
        return destinationObject;
    }

}
