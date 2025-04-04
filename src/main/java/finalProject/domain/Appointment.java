package finalProject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_generator")
    @SequenceGenerator(
            name = "appointment_generator",
            sequenceName = "appointment_sequence_name",
            allocationSize = 1
    )
    private int appointmentId;
    private String type;
    private String status;
    private String description;
    private LocalDate requestDate;
    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonBackReference(value = "patientAppointmentReference")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "providerId")
    @JsonBackReference(value = "ProviderAppointmentReference")
    private HealthCareProvider healthCareProvider;

    public Appointment() {
    }

    public Appointment(int appointmentId, String type, String status, String description, LocalDate requestDate, Patient patient, HealthCareProvider healthCareProvider) {
        this.appointmentId = appointmentId;
        this.type = type;
        this.status = status;
        this.description = description;
        this.requestDate = requestDate;
        this.patient = patient;
        this.healthCareProvider = healthCareProvider;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public HealthCareProvider getHealthCareProvider() {
        return healthCareProvider;
    }

    public void setHealthCareProvider(HealthCareProvider healthCareProvider) {
        this.healthCareProvider = healthCareProvider;
    }
}
