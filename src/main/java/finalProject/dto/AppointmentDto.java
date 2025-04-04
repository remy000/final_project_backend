package finalProject.dto;


import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;


import java.time.LocalDate;

public class AppointmentDto {
    private int appointmentId;
    private String type;
    private String status;
    private String description;
    private LocalDate requestDate;
    private int patientId;
    private String names;
    private String email;
    private int providerId;
    private String providerNames;

    public AppointmentDto() {
    }

    public AppointmentDto(int appointmentId, String type, String status, String description, LocalDate requestDate, int patientId, String names, String email, int providerId, String providerNames) {
        this.appointmentId = appointmentId;
        this.type = type;
        this.status = status;
        this.description = description;
        this.requestDate = requestDate;
        this.patientId = patientId;
        this.names = names;
        this.email = email;
        this.providerId = providerId;
        this.providerNames = providerNames;
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

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getProviderNames() {
        return providerNames;
    }

    public void setProviderNames(String providerNames) {
        this.providerNames = providerNames;
    }
}
