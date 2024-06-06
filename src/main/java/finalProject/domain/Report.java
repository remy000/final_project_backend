package finalProject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_generator")
    @SequenceGenerator(
            name = "report_generator",
            sequenceName = "report_sequence_name",
            allocationSize = 1
    )
    private int reportId;
    private String title;
    private String recommendations;
    private String improvements;
    private LocalDate reportDate;
    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonBackReference(value = "patientReportReference")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "providerId")
    @JsonBackReference(value = "ProviderReportReference")
    private HealthCareProvider healthCareProvider;

    public Report() {
    }

    public Report(int reportId, String title, String recommendations, String improvements, LocalDate reportDate, Patient patient, HealthCareProvider healthCareProvider) {
        this.reportId = reportId;
        this.title = title;
        this.recommendations = recommendations;
        this.improvements = improvements;
        this.reportDate = reportDate;
        this.patient = patient;
        this.healthCareProvider = healthCareProvider;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getImprovements() {
        return improvements;
    }

    public void setImprovements(String improvements) {
        this.improvements = improvements;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
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
