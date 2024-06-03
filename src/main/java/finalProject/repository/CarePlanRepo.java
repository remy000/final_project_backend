package finalProject.repository;

import finalProject.domain.CarePlan;
import finalProject.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarePlanRepo extends JpaRepository<CarePlan,Integer> {
    List<CarePlan>findByPatient(Patient patient);
}
