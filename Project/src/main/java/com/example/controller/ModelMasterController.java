package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.ModelMaster;
import com.example.service.ModelService;

@RestController
@RequestMapping("/api/models")
public class ModelMasterController {
    @Autowired
    private ModelService service;

    @GetMapping
    public List<ModelMaster> getAllModels() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ModelMaster getById(@PathVariable Integer id) {
        return service.getById(id);
    }
}
