package com.rentify.rentify.controllers;

import com.rentify.rentify.entities.Property;
import com.rentify.rentify.entities.Review;
import com.rentify.rentify.repos.ReviewRepository;
import com.rentify.rentify.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ReviewRestController {

    private final ReviewRepository reviewRepository;
    private final PropertyService propertyService;

    public ReviewRestController(ReviewRepository reviewRepository, PropertyService propertyService) {
        this.reviewRepository = reviewRepository;
        this.propertyService = propertyService;
    }

    @PostMapping("/properties/{propertyId}/reviews")
    public Review addReview(@PathVariable Long propertyId, @RequestBody Review review) {
        Property property = propertyService.getPropertyById(propertyId);
        review.setProperty(property);
        return reviewRepository.save(review);
    }

    @GetMapping("/properties/{propertyId}/reviews")
    public List<Review> getReviewsByProperty(@PathVariable Long propertyId) {
        return reviewRepository.findByPropertyId(propertyId);
    }
}