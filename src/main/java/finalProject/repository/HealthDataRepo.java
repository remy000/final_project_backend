package finalProject.repository;

import finalProject.domain.HealthData;
import finalProject.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthDataRepo extends JpaRepository<HealthData,Integer> {
    List<HealthData> findByPatient(Patient patient);
}
