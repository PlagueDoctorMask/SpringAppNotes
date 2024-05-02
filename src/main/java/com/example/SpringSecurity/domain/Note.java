package com.example.SpringSecurity.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Сущность Заметка
 * @id - id заметки
 * @header - заголовок заметки
 * @body - тело заметки
 * @creationDate - дата создания заметки( ставиться автоматичсеки при создании)
 */
@Data
@Entity
@Table(name = "notes")
public class Note{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String header;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private LocalDateTime creationDate;
}
