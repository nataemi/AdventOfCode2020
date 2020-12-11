package com.adventofcode.adventofcode2020.tasks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  SeatStatus {
    EMPTY("L"),
    OCCUPIED("#"),
    FLOOR(".");

    private String sign;

}
