package project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.entities.Course;
import project.services.IServices;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("Bridge")
@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*")
public class RestController {
    private final IServices services;



    @PostMapping("/addCourse")
    public Course addCourse(@RequestBody Course course){
        return services.addCourse(course);
    }

    @PutMapping("/updateCourse")
    public Course updateCourse(@RequestBody Course updatedCourse) {
        return services.updateCourse(updatedCourse);
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public void deleteCourse(@PathVariable int courseId) {
        services.deleteCourse(courseId);
    }

    @GetMapping("/")
    public List<Course> getAllCourses() {
        return services.getAllCourses();
    }






}
