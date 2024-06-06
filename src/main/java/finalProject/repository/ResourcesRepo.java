package finalProject.repository;

import finalProject.domain.EducationResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResourcesRepo extends JpaRepository<EducationResource,Integer> {
    List<EducationResource>findByCategory(String category);
}
