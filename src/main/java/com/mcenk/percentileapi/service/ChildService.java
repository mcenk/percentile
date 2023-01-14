package com.mcenk.percentileapi.service;

import com.mcenk.percentileapi.model.Child;
import com.mcenk.percentileapi.repository.ChildRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {

    private final ChildRepository childRepository;
    Logger logger= LoggerFactory.getLogger(ChildService.class);


    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public Child createChild(Child child){

        return  childRepository.save(child);
    }
    public List<Child> getAllChild(){
        List<Child> list= childRepository.findAll();
        list.stream().forEach(e->e.setAge(e.getAge()));
        return list;
    }

    public Child getChildById(Long id) {
        // costum exception yazilacak
        return childRepository.findById(id).orElseThrow(()-> new RuntimeException());
    }
}
