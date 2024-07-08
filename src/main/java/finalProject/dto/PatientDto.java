package finalProject.dto;

import java.time.LocalDate;

public class PatientDto {
    private int patientId;
    private String names;
    private String email;
    private String phoneNumber;
    private String bloodGroup;
    private LocalDate birthDate;
    private String weight;
    private String gender;
    private int age;
    private String address;
    private String sickness;
    private String allergies;
    private String roles;
    private String assignedProvider;
    private String password;
    private int providerId;


    public PatientDto() {
    }

    public PatientDto(int patientId, String names, String email, String phoneNumber, String bloodGroup, LocalDate birthDate, String weight, String gender, int age, String address, String sickness, String allergies, String roles, String assignedProvider, String password, int providerId) {
        this.patientId = patientId;
        this.names = names;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bloodGroup = bloodGroup;
        this.birthDate = birthDate;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.sickness = sickness;
        this.allergies = allergies;
        this.roles = roles;
        this.assignedProvider = assignedProvider;
        this.password = password;
        this.providerId = providerId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAssignedProvider() {
        return assignedProvider;
    }

    public void setAssignedProvider(String assignedProvider) {
        this.assignedProvider = assignedProvider;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }
}
