package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {



}