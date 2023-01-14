package com.mcenk.percentileapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavePercentileRequest {

    private Float height;
    private Float weight;
    private Float headCirc;
    private LocalDateTime createdTime;
    private Long childId;



}
