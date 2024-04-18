package com.example.phonestudentproject.model.Enum;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

@Getter
@AllArgsConstructor
public enum RegionEnum {

    RU ("+7", "Russia"),
    BY("+375", "Belarus"),
    UA("+380", "Ukraine"),
    DF("EMPTY", "EMPTY");

    private final String value;
    private final String country;

    public static RegionEnum fromValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return DF;
        }

        return EnumSet.allOf(RegionEnum.class).stream()
                .filter(item -> value.equals(item.getValue()))
                .findFirst()
                .orElse(DF);
    }
}
