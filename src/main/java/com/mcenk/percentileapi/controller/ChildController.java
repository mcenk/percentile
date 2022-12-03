package com.mcenk.percentileapi.controller;

import com.mcenk.percentileapi.model.Child;
import com.mcenk.percentileapi.service.ChildService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/child")
public class ChildController {

    private final ChildService childService;
    Logger logger= LoggerFactory.getLogger(ChildController.class);
    public ChildController(ChildService childService) {
        this.childService = childService;
    }
    @PostMapping("/savechild")
    public ResponseEntity<Child> createChild (@RequestBody Child child){

//       logger.warn(child.getBirthday().toString());

        return ResponseEntity.ok(childService.createChild(child));
    }

    @GetMapping

    public List<Child> getChild(){
        return (childService.getAllChild());
    }

    @GetMapping("/{id}")
    public Child getChildById(@PathVariable Long id){

        return childService.getChildById(id);

    }


}
