package com.app.kidspainting.Controllers;

import java.util.List;
import java.util.Optional;

import com.app.kidspainting.Repositorys.CourseRepository;
import com.app.kidspainting.Utils.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "/api/v1/course")
    public List<Course> listAll() {
        List<Course> courseAll = courseRepository.findAll();
        return courseAll;
    }

    @GetMapping(value = "/api/v1/course/{id}")
    public Course findById(@PathVariable Integer id){
        Optional<Course> course = courseRepository.findById(id);
        return course.get();
    }

    @PostMapping(value = "/api/v1/course")
    public Course addCourse(@RequestBody Course NewCourse){
        courseRepository.save(NewCourse);
        return NewCourse;
    }

    @DeleteMapping(value = "/api/v1/course/{id}")
    public void deleteCourse(@PathVariable Integer id) {
        courseRepository.deleteById(id);
    }

    @PutMapping(value = "/api/v1/course/{id}")
    public void updateCourse(@PathVariable Integer id, @RequestBody Course NewCourse){
        Course course = courseRepository.getById(id);
        course.setCourseName(NewCourse.getCourseName());
        courseRepository.save(course);
    }
}
