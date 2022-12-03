package com.mcenk.percentileapi.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
@Table(name = "child")
@Data
public class Child {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "50"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "3")
            }
    )
    private long childId;

    private String name;
    private String gender;
    private LocalDate birthday;
    private Integer age;
    private Integer birthWeight;
    private Integer birthHeight;
    private String childInfo;


    public int getAge(){
        LocalDate localDate= LocalDate.now();
         return Period.between(getBirthday(),localDate).getYears();
    }

}