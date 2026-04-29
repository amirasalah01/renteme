package com.rentify.rentify.service;

import com.rentify.rentify.entities.Property;
import com.rentify.rentify.repos.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));
    }

    @Override
    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public Property updateProperty(Long id, Property property) {
        Property existing = getPropertyById(id);
        existing.setTitle(property.getTitle());
        existing.setDescription(property.getDescription());
        existing.setCity(property.getCity());
        existing.setAddress(property.getAddress());
        existing.setPrice(property.getPrice());
        existing.setType(property.getType());
        existing.setBedrooms(property.getBedrooms());
        existing.setImageUrl(property.getImageUrl());
        return propertyRepository.save(existing);
    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
