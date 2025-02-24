package com.directa24.main.challenge.limepay.dto;

import lombok.Data;

import java.util.List;

@Data
public class DirectorsDto {
    private List<String> directors;

    public DirectorsDto(List<String> directors){
        this.directors = directors;
    }
}
