package finalProject.repository;

import finalProject.domain.HealthData;
import finalProject.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HealthDataRepo extends JpaRepository<HealthData,Integer> {
    List<HealthData> findByPatient(Patient patient);
    Optional<HealthData>findByPatientAndRegDate(Patient patient, LocalDate regDate);
}
