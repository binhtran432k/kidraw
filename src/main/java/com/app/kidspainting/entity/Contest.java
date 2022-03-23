package com.app.kidspainting.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contest")
public class Contest extends StudyObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name; 
    @Column(name = "body", nullable = false)
    private String body;
    @Builder.Default
    @Column(name = "registration_time", nullable = false)
    private LocalDateTime registrationTime = LocalDateTime.now();
    @Builder.Default
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime = LocalDateTime.now();
    @Builder.Default
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime = LocalDateTime.now();
    @Builder.Default
    @Column(name = "max_participant", nullable = false)
    private Integer maxParticipant = 0;
    @Builder.Default
    @Column(name = "visibility", nullable = false)
    private Boolean visibility = false;
}
