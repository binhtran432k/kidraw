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
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer courseId;
    @Column(name = "CourseName", nullable = false)
    private String courseName; 
    @Column(name = "CourseDescription", nullable = false)
    private String courseDescription;
    @Column(name = "CoursePrice", nullable = false)
    private Double coursePrice;
    @Column(name = "MaxCourseParticipant", nullable = false)
    private Integer maxCourseParticipant;
    @Column(name = "NumOfSection", nullable = false)
    private Integer numOfSection;
}
