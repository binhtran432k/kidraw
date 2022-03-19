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
@Table(name = "ClassArt")
public class ClassArt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer classArtId;
    @Column(name = "ClassArtName", nullable = false)
    private String classArtName; 
    @Column(name = "NumOfStudent", nullable = false)
    private Integer numOfStudent;
}
