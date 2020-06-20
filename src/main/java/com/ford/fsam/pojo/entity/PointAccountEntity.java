package com.ford.fsam.pojo.entity;

import com.ford.fsam.common.enums.PointAccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Builder
@Entity
@Table(name = "point_account")
@NoArgsConstructor
@AllArgsConstructor
public class PointAccountEntity {
    @Id
    private Long id;
    private Long userId;
    private Integer balance;
    @Enumerated(EnumType.STRING)
    private PointAccountStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointAccountEntity)) return false;
        PointAccountEntity entity = (PointAccountEntity) o;
        return Objects.equals(getUserId(), entity.getUserId()) &&
                getStatus() == entity.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getStatus());
    }
}
