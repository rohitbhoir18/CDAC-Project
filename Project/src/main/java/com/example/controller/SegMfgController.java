package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.SegMfgMaster;
import com.example.service.SegMfgService;

@RestController
@RequestMapping("/api/seg-mfg")
//@CrossOrigin(origins = "*")
public class SegMfgController {

    @Autowired
    private SegMfgService service;

    @GetMapping
    public List<SegMfgMaster> getAll() 
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SegMfgMaster getById(@PathVariable int id) 
    {
        return service.getById(id);
    }

}

