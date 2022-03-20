package com.app.kidspainting.Utils;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer courseId;

    @Column(name = "CourseName", nullable = false, unique = true)
    private String courseName; 

    @Column(name = "CourseDescription", nullable = false)
    private String courseDescription;

    @Column(name = "CoursePrice", nullable = false)
    private Double coursePrice;

    @Column(name = "MaxCourseParticipant", nullable = false)
    private Integer maxCourseParticipant;

    @Column(name = "SumOfSection", nullable = false)
    private Integer sumOfSection;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "typeArtId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TypeArt myType;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "levelId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Level myLevel;

    @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      mappedBy = "joinCourse")
    @JsonIgnore
    private Set <Student> students;
}
