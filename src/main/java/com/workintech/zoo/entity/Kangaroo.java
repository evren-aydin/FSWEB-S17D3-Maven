package com.workintech.zoo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Kangaroo {

    private int id;
    private String name;
    private double height;
    private double weight;
    private String gender;
    private boolean isAggressive;

}
