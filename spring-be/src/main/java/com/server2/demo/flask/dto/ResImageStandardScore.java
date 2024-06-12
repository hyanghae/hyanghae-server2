package com.server2.demo.flask.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResImageStandardScore {
    private String name;
    private double score;

}