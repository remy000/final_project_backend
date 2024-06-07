package finalProject.dto;

import java.time.LocalDate;

public class HealthDataDto {
    private int dataId;
    private float calories;
    private float bodyWater;
    private float exercisesDuration;
    private float heartRate;
    private float bloodPressure;
    private float respLevel;
    private float stressLevel;
    private LocalDate regDate;
    private int patientId;
    private String names;
    private String sickness;

    public HealthDataDto() {
    }

    public HealthDataDto(int dataId, float calories, float bodyWater, float exercisesDuration, float heartRate, float bloodPressure, float respLevel, float stressLevel, LocalDate regDate, int patientId, String names, String sickness) {
        this.dataId = dataId;
        this.calories = calories;
        this.bodyWater = bodyWater;
        this.exercisesDuration = exercisesDuration;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.respLevel = respLevel;
        this.stressLevel = stressLevel;
        this.regDate = regDate;
        this.patientId = patientId;
        this.names = names;
        this.sickness = sickness;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
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
}
