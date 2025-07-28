package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.VehicleDetail;
import com.example.service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleDetailController {
    @Autowired
    private VehicleService service;

    @GetMapping
    public List<VehicleDetail> getAllVehicles() {
        return service.getAll();
    }
}
