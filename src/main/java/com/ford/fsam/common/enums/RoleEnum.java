package com.ford.fsam.common.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    OPERATOR(1L,"OPERATOR"),SALES(2L,"SALES");
    private Long id;
    private String name;

    RoleEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }


}