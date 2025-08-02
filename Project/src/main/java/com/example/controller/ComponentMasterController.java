package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.ComponentMaster;
import com.example.service.ComponentService;

@RestController
@RequestMapping("/api/components")
public class ComponentMasterController {
    @Autowired
    private ComponentService service;

    @GetMapping
    public List<ComponentMaster> getAllComponents() {
        return service.getAll();
    }
}
