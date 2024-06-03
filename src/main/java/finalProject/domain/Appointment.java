package finalProject.domain;

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
    private LocalDate requestDate;
    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "providerId")
    private HealthCareProvider healthCareProvider;

    public Appointment() {
    }

    public Appointment(int appointmentId, String type, String status, LocalDate requestDate, Patient patient, HealthCareProvider healthCareProvider) {
        this.appointmentId = appointmentId;
        this.type = type;
        this.status = status;
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
