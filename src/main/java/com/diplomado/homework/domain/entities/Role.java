package com.diplomado.homework.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = Index.ROLE_TABLE_NAME)
public final class Role implements Index {

    @Id
    @SequenceGenerator(name = ROLE_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ROLE_SEQUENCE)
    private Integer id;
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}