package com.example.phonestudentproject.model.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegionEnum {

    RU ("+7", "Russia"),
    BY("+375", "Belarus"),
    UA("+380", "Ukraine");

    private final String value;
    private final String country;
}
