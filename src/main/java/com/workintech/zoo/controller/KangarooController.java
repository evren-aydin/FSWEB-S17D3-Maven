package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        this.kangaroos=new HashMap<>();

        kangaroos.put(1,new Kangaroo(1,"fred",50.20,30.06,"erkek",true));
    }

    @GetMapping("/kangaroos")
    public List<Kangaroo> getAllList(){
        return kangaroos.values().stream().toList();
    }
    @GetMapping("/kangaroos/{id}")
    public Kangaroo returnKangarooById(@PathVariable int id){
        if(!kangaroos.containsKey(id)){
            log.error("Kangaroo with id {} not found", id);
            throw new ZooException("hata", HttpStatus.BAD_REQUEST);
        }
        return kangaroos.get(id);
    }
    @PostMapping("/kangaroos")
    public void addKangaroo(@RequestBody Kangaroo kangaroo){
        if(kangaroos.containsValue(kangaroo)){
            log.error("Kangaroo with id {} already exists", kangaroo.getId());
            throw new ZooException("Kangaroo already exists", HttpStatus.BAD_REQUEST);
        }else{
            kangaroos.put(kangaroo.getId(),kangaroo);
        }
    }

    @PutMapping("/kangaroos/{id}")
    public void changeKangaroo(@PathVariable int id,@RequestBody Kangaroo newKangaroo){
    Kangaroo kangaroo= kangaroos.get(id);

    if(!kangaroos.containsKey(id) || kangaroo==null){
        log.error("Kangaroo with id {} not found", id);
        throw new ZooException("Kangaroo not found", HttpStatus.BAD_REQUEST);
    }else {
        kangaroo.setAggressive(newKangaroo.isAggressive());
        kangaroo.setName(newKangaroo.getName());
        kangaroo.setWeight(newKangaroo.getWeight());
        kangaroo.setHeight(newKangaroo.getHeight());
        kangaroo.setGender(newKangaroo.getGender());
    }}

    @DeleteMapping("/kangaroos/{id}")
    public Kangaroo deleteObje(@PathVariable int id){

        if(!kangaroos.containsKey(id)){
            log.error("Kangaroo with id {} not found", id);
            throw new ZooException("Kangaroo not found", HttpStatus.BAD_REQUEST);
        }


        return kangaroos.remove(id);
    }
}
