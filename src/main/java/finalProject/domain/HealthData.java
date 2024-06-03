package finalProject.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table
public class HealthData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "health_generator")
    @SequenceGenerator(
            name = "health_generator",
            sequenceName = "health_sequence_name",
            allocationSize = 1
    )
    private int id;
    private float calories;
    private float bodyWater;
    private float exercisesDuration;
    private float heartRate;
    private float bloodPressure;
    private float respLevel;
    private float stressLevel;
    private LocalDate regDate;
    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    public HealthData() {
    }

    public HealthData(int id, float calories, float bodyWater, float exercisesDuration, float heartRate, float bloodPressure, float respLevel, float stressLevel, LocalDate regDate, Patient patient) {
        this.id = id;
        this.calories = calories;
        this.bodyWater = bodyWater;
        this.exercisesDuration = exercisesDuration;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.respLevel = respLevel;
        this.stressLevel = stressLevel;
        this.regDate = regDate;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getBodyWater() {
        return bodyWater;
    }

    public void setBodyWater(float bodyWater) {
        this.bodyWater = bodyWater;
    }

    public float getExercisesDuration() {
        return exercisesDuration;
    }

    public void setExercisesDuration(float exercisesDuration) {
        this.exercisesDuration = exercisesDuration;
    }

    public float getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(float heartRate) {
        this.heartRate = heartRate;
    }

    public float getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(float bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public float getRespLevel() {
        return respLevel;
    }

    public void setRespLevel(float respLevel) {
        this.respLevel = respLevel;
    }

    public float getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(float stressLevel) {
        this.stressLevel = stressLevel;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
