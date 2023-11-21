package com.diplomado.homework.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = Index.USER_ROLE_TABLE_NAME)
public final class UserRole implements Index{

    @Id
    @SequenceGenerator(name = USER_ROLE_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USER_ROLE_SEQUENCE)
    private Integer id;
    private Boolean active;
    @Column(name = CREATED_AT, columnDefinition = TIMESTAMP)
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = USER_ID)
    private User users;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = ROLE_ID)
    private Role roles;

    public UserRole() {
    }

    public UserRole(Boolean active, LocalDateTime createdAt, User users, Role roles) {
        this.active = active;
        this.createdAt = createdAt;
        this.users = users;
        this.roles = roles;
    }
}