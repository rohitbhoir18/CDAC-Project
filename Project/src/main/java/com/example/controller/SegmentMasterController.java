package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.SegmentMaster;
import com.example.service.SegmentService;

@RestController
public class SegmentMasterController {
    @Autowired
    private SegmentService service;

    @GetMapping("/api/segments")
    public List<SegmentMaster> getAllSegments() {
        return service.getAllSeg();
    }
}