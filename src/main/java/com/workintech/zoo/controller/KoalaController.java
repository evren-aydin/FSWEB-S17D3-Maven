package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class KoalaController {

    private Map<Integer, Koala> koalas;


    @PostConstruct
    public void init(){
        this.koalas=new HashMap<>();

        koalas.put(1,new Koala(3,"Recep",80,12,"dişi"));
    }

    @GetMapping("/koalas")
    public List<Koala> getAllList(){
        return koalas.values().stream().toList();
    }
    @GetMapping("/koalas/{id}")
    public Koala returnKoalaById(@PathVariable int id){
        if(!koalas.containsKey(id)){
            log.error("Koala with id {} not found", id);
            throw new ZooException("Koala not found", HttpStatus.BAD_REQUEST);
        }
        return koalas.get(id);
    }
    @PostMapping("/koalas")
    public void addKoala(@RequestBody Koala koala){
        if(koalas.containsValue(koala)){
            log.error("Koala with id {} already exists", koala.getId());
            throw new ZooException("Koala already exists", HttpStatus.BAD_REQUEST);
        }else{
            koalas.put(koala.getId(),koala);
        }
    }

    @PutMapping("/koalas/{id}")
    public void changeKangaroo(@PathVariable int id,@RequestBody Koala newKoala){
        Koala koala= koalas.get(id);

        if(!koalas.containsKey(id) || koala==null){
            log.error("Koala with id {} not found", id);
            throw new ZooException("Koala not found", HttpStatus.BAD_REQUEST);
        }else {
            koala.setName(newKoala.getName());
            koala.setWeight(newKoala.getWeight());
            koala.setSleepHour(newKoala.getSleepHour());
            koala.setGender(newKoala.getGender());
        }}

    @DeleteMapping("/koalas/{id}")
    public Koala deleteObje(@PathVariable int id){

        if(!koalas.containsKey(id)){
            log.error("Koala with id {} not found", id);
            throw new ZooException("Koala not found", HttpStatus.BAD_REQUEST);
        }

        return koalas.remove(id);
    }

}
