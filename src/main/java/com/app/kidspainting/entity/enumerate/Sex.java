package com.app.kidspainting.entity.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sex {
    MALE("M"),
    FEMALE("F"),
    UNDEFINE("U");
    private String code;
}
