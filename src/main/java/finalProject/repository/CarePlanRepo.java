package finalProject.repository;

import finalProject.domain.CarePlan;
import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CarePlanRepo extends JpaRepository<CarePlan,Integer> {
    Optional<CarePlan>findByPatient(Patient patient);
    Optional<CarePlan> findByPatientAndHealthCareProvider(Patient patient, HealthCareProvider healthCareProvider);
}
