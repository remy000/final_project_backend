package finalProject.dto;

import java.time.LocalDate;

public class CarePlanDto {
    private int planId;
    private String personalizedPlan;
    private String medicalPlan;
    private String exercisePlan;
    private String dietPlan;
    private LocalDate creationDate;
    private int patientId;
    private String names;
    private String sickness;
    private int providerId;

    public CarePlanDto() {
    }

    public CarePlanDto(int planId, String personalizedPlan, String medicalPlan, String exercisePlan, String dietPlan, LocalDate creationDate, int patientId, String names, String sickness, int providerId) {
        this.planId = planId;
        this.personalizedPlan = personalizedPlan;
        this.medicalPlan = medicalPlan;
        this.exercisePlan = exercisePlan;
        this.dietPlan = dietPlan;
        this.creationDate = creationDate;
        this.patientId = patientId;
        this.names = names;
        this.sickness = sickness;
        this.providerId = providerId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPersonalizedPlan() {
        return personalizedPlan;
    }

    public void setPersonalizedPlan(String personalizedPlan) {
        this.personalizedPlan = personalizedPlan;
    }

    public String getMedicalPlan() {
        return medicalPlan;
    }

    public void setMedicalPlan(String medicalPlan) {
        this.medicalPlan = medicalPlan;
    }

    public String getExercisePlan() {
        return exercisePlan;
    }

    public void setExercisePlan(String exercisePlan) {
        this.exercisePlan = exercisePlan;
    }

    public String getDietPlan() {
        return dietPlan;
    }

    public void setDietPlan(String dietPlan) {
        this.dietPlan = dietPlan;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    public String getSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }
}
