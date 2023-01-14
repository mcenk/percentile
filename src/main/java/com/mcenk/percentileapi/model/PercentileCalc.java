package com.mcenk.percentileapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "percentile")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PercentileCalc {
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
    private Long id;
    private Float height;
    private Float weight;
    private Float headCirc;
    private LocalDateTime createdTime;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "child_id")
    @JsonIgnore
    private Child child;


}
