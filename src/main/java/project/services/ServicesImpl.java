package project.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.entities.Course;
import project.repositories.CourseRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServicesImpl implements IServices {




    private final CourseRepository courseRepository;



    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course updatedCourse) {
        return courseRepository.save(updatedCourse);
    }

    @Override
    public void deleteCourse(int courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }




}
