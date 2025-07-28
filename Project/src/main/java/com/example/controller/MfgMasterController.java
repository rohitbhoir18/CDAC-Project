package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.MfgMaster;
import com.example.service.MfgService;

@RestController
@RequestMapping("/api/manufacturers")
public class MfgMasterController {
    @Autowired
    private MfgService service;

    @GetMapping
    public List<MfgMaster> getAllManufacturers() {
        return service.getAll();
    }
}
