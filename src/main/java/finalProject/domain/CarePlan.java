package finalProject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table
public class CarePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_generator")
    @SequenceGenerator(
            name = "plan_generator",
            sequenceName = "plan_sequence_name",
            allocationSize = 1
    )
    private int planId;
    private String personalizedPlan;
    private String medicalPlan;
    private String exercisePlan;
    private String dietPlan;
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonBackReference(value = "patientPlanReference")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "providerId")
    @JsonBackReference(value = "ProviderPlanReference")
    private HealthCareProvider healthCareProvider;

    public CarePlan() {
    }

    public CarePlan(int planId, String personalizedPlan, String medicalPlan, String exercisePlan, String dietPlan, LocalDate creationDate, Patient patient, HealthCareProvider healthCareProvider) {
        this.planId = planId;
        this.personalizedPlan = personalizedPlan;
        this.medicalPlan = medicalPlan;
        this.exercisePlan = exercisePlan;
        this.dietPlan = dietPlan;
        this.creationDate = creationDate;
        this.patient = patient;
        this.healthCareProvider = healthCareProvider;
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
