package com.mcenk.percentileapi.service;

import com.mcenk.percentileapi.model.Child;
import com.mcenk.percentileapi.repository.ChildRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {

    private final ChildRepository childRepository;


    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public Child createChild(Child child){
       return  childRepository.save(child);
    }
    public List <Child> getAllChild(){
        return childRepository.findAll();

    }
}
