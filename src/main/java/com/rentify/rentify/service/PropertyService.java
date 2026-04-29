package com.rentify.rentify.service;

import com.rentify.rentify.entities.Property;

import java.util.List;

public interface PropertyService {

    List<Property> getAllProperties();

    Property getPropertyById(Long id);

    Property saveProperty(Property property);

    Property updateProperty(Long id, Property property);

    void deleteProperty(Long id);
}
