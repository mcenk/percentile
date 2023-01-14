package com.mcenk.percentileapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Entity
@Table(name = "child")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator = "sequence-generator")
//    @GenericGenerator(
//            name = "sequence-generator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
//                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "50"),
//                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "3")
//            }
//    )

    private long id;
    private String name;
    private String gender;
    private LocalDate birthday;
    private Integer age;
    private Integer birthWeight;
    private Integer birthHeight;
    private String childInfo;
    // her bir cocuga ait hesaplamalari tutacagimiz alan

//    @OneToMany (mappedBy = "child", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
////    private Set<PercentileCalc> percentileCalcs;


//    public int getAge(){
//        LocalDate localDate= LocalDate.now();
//         return Period.between(getBirthday(),localDate).getYears();
//    }

}