package com.app.kidspainting.entity;

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
@Table(name = "course")
public class Course extends StudyObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Builder.Default
    @Column(name = "price", nullable = false)
    private Double price = 0.0;
    @Builder.Default
    @Column(name = "max_participant", nullable = false)
    private Integer maxParticipant = 0;
    @Builder.Default
    @Column(name = "visibility", nullable = false)
    private Boolean visibility = false;
    // TODO: use separate section
    // @Column(name = "num_of_section", nullable = false)
    // private Integer sumOfSection;
}
