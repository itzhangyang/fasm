package com.ford.fsam.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    private Long id;
    private String mobilePhone;
    private String userName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity entity = (UserEntity) o;
        return getMobilePhone().equals(entity.getMobilePhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMobilePhone());
    }
}
