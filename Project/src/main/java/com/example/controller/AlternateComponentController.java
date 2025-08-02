package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.AlternateComponentMaster;
import com.example.service.AlternateCompService;

@RestController
@RequestMapping("/api/alternates")
public class AlternateComponentController {
    @Autowired
    private AlternateCompService service;

    @GetMapping
    public List<AlternateComponentMaster> getAllAlternates() {
        return service.getAll();
    }
}
