package finalProject.repository;

import finalProject.domain.Patient;
import finalProject.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepo extends JpaRepository<Report,Integer> {
    List<Report>findByPatient(Patient patient);
}
