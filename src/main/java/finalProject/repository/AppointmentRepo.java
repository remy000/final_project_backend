package finalProject.repository;

import finalProject.domain.Appointment;
import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {

    List<Appointment>findByHealthCareProvider(HealthCareProvider healthCareProvider);
}
