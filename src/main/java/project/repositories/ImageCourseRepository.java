package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entities.ImageCourse;

import java.util.Optional;

@Repository
public interface ImageCourseRepository extends JpaRepository<ImageCourse, Long> {
    Optional<ImageCourse> findById(Long id);

    Optional<ImageCourse> findByIdCourse(int idCourse);
}


