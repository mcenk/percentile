package com.mcenk.percentileapi.controller;

import com.mcenk.percentileapi.model.Child;
import com.mcenk.percentileapi.service.ChildService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/child")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }
    @PostMapping("/savechild")
    public ResponseEntity<Child> createChild (@RequestBody Child child){

        return ResponseEntity.ok(childService.createChild(child));
    }

    @GetMapping
    public List<Child> getChild(){
        return (childService.getAllChild());
    }


}
