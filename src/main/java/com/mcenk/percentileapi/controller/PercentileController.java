package com.mcenk.percentileapi.controller;

import com.mcenk.percentileapi.dto.SavePercentileRequest;
import com.mcenk.percentileapi.exception.UserNotFoundException;
import com.mcenk.percentileapi.model.PercentileCalc;
import com.mcenk.percentileapi.service.PercentileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/percentile")
public class PercentileController {

    private final PercentileService percentileService;

    public PercentileController(PercentileService percentileService) {
        this.percentileService = percentileService;
    }

    @PostMapping
    public ResponseEntity<PercentileCalc> savePercentile(@RequestBody SavePercentileRequest from){
        return ResponseEntity.ok(percentileService.savePercentile(from));
    }

    @GetMapping
    List<PercentileCalc> getAllPercentile(){
        return percentileService.getAllPercentile();

    }
    // RESPONSE ENTITY DONUSTUR
    @GetMapping("/{id}")
    PercentileCalc getPercentileById(@PathVariable Long id){
        return percentileService.getPercentileById(id).orElseThrow(()-> new UserNotFoundException(id));


    }


}
