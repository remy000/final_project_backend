package finalProject.repository;

import finalProject.domain.EducationResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcesRepo extends JpaRepository<EducationResource,Integer> {
}
