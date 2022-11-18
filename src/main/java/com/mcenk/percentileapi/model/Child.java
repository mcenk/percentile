package com.mcenk.percentileapi.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "child")
@Data
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private Date birthday;
    private Integer birthWeight;
    private Integer birthHeight;
    private String childInfo;
}
