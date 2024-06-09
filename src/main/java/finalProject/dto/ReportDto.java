package finalProject.dto;
import java.time.LocalDate;

public class ReportDto {
    private int reportId;
    private String title;
    private String recommendations;
    private String improvements;
    private LocalDate reportDate;
    private int patientId;
    private String patientNames;
    private int providerId;

    public ReportDto() {
    }
    public ReportDto(int reportId, String title, String recommendations, String improvements, LocalDate reportDate, int patientId, String patientNames, int providerId) {
        this.reportId = reportId;
        this.title = title;
        this.recommendations = recommendations;
        this.improvements = improvements;
        this.reportDate = reportDate;
        this.patientId = patientId;
        this.patientNames = patientNames;
        this.providerId = providerId;
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

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientNames() {
        return patientNames;
    }

    public void setPatientNames(String patientNames) {
        this.patientNames = patientNames;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }
}
