package com.ford.fsam.pojo.dto;

import com.ford.fsam.common.enums.RoleEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Role {
    private Long id;
    private String name;
    public static Role  of(RoleEnum roleEnum){
        return Role.builder().id(roleEnum.getId()).name(roleEnum.getName()).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getId().equals(role.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
