package project.services;

import project.entities.Course;

import java.util.List;

public interface IServices {




   public Course addCourse(Course course);

    public Course updateCourse(Course updatedCourse);

    void deleteCourse(int courseId);

    public List<Course> getAllCourses();

   public Course getCourseById(int courseId);
}
