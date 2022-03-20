package com.app.kidspainting.Utils;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Student")
public class Student extends User {
    
    @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
    @JoinTable(name = "joined-course",
        joinColumns = { @JoinColumn(name = "userId") },
        inverseJoinColumns = { @JoinColumn(name = "courseId") })
    @JsonIgnore
    private Set <Course> joinCourse = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "joined-contest",
      joinColumns = { @JoinColumn(name = "userId") },
      inverseJoinColumns = { @JoinColumn(name = "contestId") })
    @JsonIgnore
    private Set <Contest> joinContest;
}
