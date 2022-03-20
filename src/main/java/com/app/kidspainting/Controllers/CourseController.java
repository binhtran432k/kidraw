package com.app.kidspainting.Controllers;

import java.util.List;
import java.util.Optional;

import com.app.kidspainting.Repositorys.CourseRepository;
import com.app.kidspainting.Repositorys.LevelRepository;
import com.app.kidspainting.Repositorys.TypeArtRepository;
import com.app.kidspainting.Utils.Course;
import com.app.kidspainting.Utils.Level;
import com.app.kidspainting.Utils.TypeArt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @Autowired 
    private TypeArtRepository typeArtRepository;

    @Autowired
    private LevelRepository levelRepository;


    // lấy tất cả khóa học
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/course")
    public List<Course> listAll() {
        List<Course> courseAll = courseRepository.findAll();
        return courseAll;
    }

    // Lấy khóa học bằng Id
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/course/{id}")
    public Course findById(@PathVariable Integer id){
        Optional<Course> course = courseRepository.findById(id);
        return course.get();
    }

    // Lấy kiểu vẽ trong khóa học bằng id
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/course/type/{id}")
    public String getTypeOfCourse(@PathVariable Integer id){
        Optional<Course> course = courseRepository.findById(id);      
        return course.get().getMyType().getTypeName();
    }

    //lấy mức độ của kháo học bằng id
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/course/level/{id}")
    public String getLevelOfCourse(@PathVariable Integer id){
        Optional<Course> course = courseRepository.findById(id);      
        return course.get().getMyLevel().getLevelName();
    }

    //Thêm khóa học
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/v1/course/{typeArtName}/{levelArtName}")
    public Course addCourse(@RequestBody Course NewCourse, @PathVariable String typeArtName, @PathVariable String levelArtName){
        Optional<TypeArt> typeArt = typeArtRepository.findByTypeName(typeArtName);
        NewCourse.setMyType(typeArt.get());
        Optional<Level> level = levelRepository.findByLevelName(levelArtName);
        NewCourse.setMyLevel(level.get());
        courseRepository.save(NewCourse);
        return NewCourse;
    }

    // Xóa khóa học
    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/api/v1/course/{id}")
    public void deleteCourse(@PathVariable Integer id) {
        courseRepository.deleteById(id);
    }

    //Chỉnh sửa khóa học
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/api/v1/course/{typeArtName}/{levelArtName}/{id}")
    public void updateCourse(@PathVariable Integer id, @PathVariable String typeArtName, @PathVariable String levelArtName, @RequestBody Course NewCourse){
        Course course = courseRepository.getById(id);
        Optional<TypeArt> typeArt = typeArtRepository.findByTypeName(typeArtName);
        NewCourse.setMyType(typeArt.get());
        Optional<Level> level = levelRepository.findByLevelName(levelArtName);
        NewCourse.setMyLevel(level.get());

        course.setCourseName(NewCourse.getCourseName());
        course.setCourseDescription(NewCourse.getCourseDescription());
        course.setCoursePrice(NewCourse.getCoursePrice());
        course.setMaxCourseParticipant(NewCourse.getMaxCourseParticipant());
        course.setSumOfSection(NewCourse.getSumOfSection());
        course.setMyLevel(NewCourse.getMyLevel());
        course.setMyType(NewCourse.getMyType());
        courseRepository.save(course);
    }
}
