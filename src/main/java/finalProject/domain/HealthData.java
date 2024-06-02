package finalProject.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table
public class HealthData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String calories;
    private String bodyWater;
    private String exercisesDuration;
    private String heartRate;
    private String bloodPressure;
    private String respLevel;
    private LocalDate regDate;
    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    public HealthData() {
    }

    public HealthData(int id, String calories, String bodyWater, String exercisesDuration, String heartRate, String bloodPressure, String respLevel, LocalDate regDate, Patient patient) {
        this.id = id;
        this.calories = calories;
        this.bodyWater = bodyWater;
        this.exercisesDuration = exercisesDuration;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.respLevel = respLevel;
        this.regDate = regDate;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getBodyWater() {
        return bodyWater;
    }

    public void setBodyWater(String bodyWater) {
        this.bodyWater = bodyWater;
    }

    public String getExercisesDuration() {
        return exercisesDuration;
    }

    public void setExercisesDuration(String exercisesDuration) {
        this.exercisesDuration = exercisesDuration;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getRespLevel() {
        return respLevel;
    }

    public void setRespLevel(String respLevel) {
        this.respLevel = respLevel;
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
