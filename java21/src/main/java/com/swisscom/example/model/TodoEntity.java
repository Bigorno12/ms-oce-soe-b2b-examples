package com.swisscom.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo")
public class TodoEntity {

    @Id
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "title", nullable = false, length = 1500)
    private String title;
}
