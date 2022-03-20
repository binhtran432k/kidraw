package com.app.kidspainting.Controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.app.kidspainting.Repositorys.CourseRepository;
import com.app.kidspainting.Repositorys.StudentRepository;
import com.app.kidspainting.Utils.Course;
import com.app.kidspainting.Utils.Student;

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
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    //Lấy mức độ dựa trên id
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/student/{id}")
    public Student setStudent(@PathVariable Integer id){
        Optional<Student> myStudent = studentRepository.findById(id);
        return myStudent.get();
    }

    //Lấy mức độ dựa trên tên
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/student/name/{name}")
    public Student getByName(@PathVariable String name){
        Optional<Student> myStudent = studentRepository.findByEmail(name);
        return myStudent.get();
    }

    //Thêm mức độ vẽ
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/v1/student")
    public Student addStudent(@RequestBody Student NewStudent){
        NewStudent.setJoinCourse(new HashSet<>());
        NewStudent.setJoinContest(new HashSet<>());
        studentRepository.save(NewStudent);
        return NewStudent;
    }

    //Thêm mức độ vẽ
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/v1/student/course/{idC}")
    public Student addStudentCourse(@RequestBody Student NewStudent, @PathVariable Integer idC){
        Optional<Course> courses = courseRepository.findById(idC);
        if (courses.isPresent()){
            Set <Course> x = new HashSet<>();
            x.add(courses.get());
            NewStudent.setJoinCourse(x);
            NewStudent.setJoinContest(new HashSet<>());
            studentRepository.save(NewStudent);
        }
        return NewStudent;
    }

    //Xóa mức độ
    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/api/v1/student/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
    }

    //Chỉnh sửa mức độ
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/api/v1/student/{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student NewStudent){
        Student myStudent = studentRepository.getById(id);
        myStudent.setFullName(NewStudent.getFullName());
        studentRepository.save(myStudent);
    }
}
