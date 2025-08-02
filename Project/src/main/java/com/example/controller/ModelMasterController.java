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

    @GetMapping("/segmfg/{segid}/{mfgid}")
    public List<ModelMaster> getModelsBySegidandMfgid(@PathVariable Integer segid , @PathVariable Integer mfgid) {
        return service.getBySegIdandMfgId(segid, mfgid);
    }

    @GetMapping("/mfg/{mfgid}")
    public List<ModelMaster> getByMfgId(@PathVariable Integer mfgid) {
        return service.getByMfgId(mfgid);
    }
    
//    @GetMapping("/minqty/{segid}")
//    public ModelMaster getMinQtyBySegId(@PathVariable int segid) {
//        return service.getMinQtyBySegId(segid);
//    }
}
