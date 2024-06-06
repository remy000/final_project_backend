package finalProject.repository;

import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient,Integer> {
    List<Patient>findByHealthCareProvider(HealthCareProvider healthCareProvider);
    Optional<Patient>findByEmail(String email);
}
