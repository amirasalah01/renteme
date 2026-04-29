package com.rentify.rentify.controllers;

import com.rentify.rentify.entities.Property;
import com.rentify.rentify.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // GET /properties — list all properties
    @GetMapping
    public String listProperties(Model model) {
        model.addAttribute("properties", propertyService.getAllProperties());
        return "properties/list";
    }

    // GET /properties/new — show add form
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("property", new Property());
        model.addAttribute("formTitle", "Add New Property");
        model.addAttribute("actionUrl", "/properties/save");
        return "properties/form";
    }

    // POST /properties/save — save a new property
    @PostMapping("/save")
    public String saveProperty(@ModelAttribute Property property) {
        propertyService.saveProperty(property);
        return "redirect:/properties";
    }

    // GET /properties/edit/{id} — show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Property property = propertyService.getPropertyById(id);
        model.addAttribute("property", property);
        model.addAttribute("formTitle", "Edit Property");
        model.addAttribute("actionUrl", "/properties/update/" + id);
        return "properties/form";
    }

    // POST /properties/update/{id} — update a property
    @PostMapping("/update/{id}")
    public String updateProperty(@PathVariable Long id, @ModelAttribute Property property) {
        propertyService.updateProperty(id, property);
        return "redirect:/properties";
    }

    // GET /properties/delete/{id} — delete a property
    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return "redirect:/properties";
    }

    // GET /properties/{id} — property details
    @GetMapping("/{id}")
    public String propertyDetails(@PathVariable Long id, Model model) {
        model.addAttribute("property", propertyService.getPropertyById(id));
        return "properties/details";
    }
}
