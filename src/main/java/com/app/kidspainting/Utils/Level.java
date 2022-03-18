package com.app.kidspainting.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Level")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer levelId;
    
    @Column(name = "LevelName", nullable = false)
    private String levelName; 
}
