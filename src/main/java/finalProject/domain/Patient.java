package finalProject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import finalProject.repository.UserInfo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Patient implements UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_generator")
    @SequenceGenerator(
            name = "patient_generator",
            sequenceName = "patient_sequence_name",
            allocationSize = 1
    )
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
    @ManyToOne
    @JoinColumn(name = "providerId")
    @JsonBackReference(value = "patientProviderReference")
    private HealthCareProvider healthCareProvider;
    @OneToMany(mappedBy = "patient")
    @JsonManagedReference("patientDataReference")
    private List<HealthData> healthData;
    @OneToOne(mappedBy = "patient")
    @JsonManagedReference("patientPlanReference")
    private CarePlan carePlans;
    @OneToMany(mappedBy = "patient")
    @JsonManagedReference("patientReportReference")
    private List<Report>reports;
    @OneToMany(mappedBy = "patient")
    @JsonManagedReference("patientAppointmentReference")
    private List<Appointment>appointments;

    public Patient() {
    }

    public Patient(int patientId, String names, String email, String phoneNumber, String bloodGroup, LocalDate birthDate, String weight, String gender, int age, String address, String sickness, String allergies, String roles, String assignedProvider, String password, HealthCareProvider healthCareProvider, List<HealthData> healthData, CarePlan carePlans, List<Report> reports, List<Appointment> appointments) {
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
        this.healthCareProvider = healthCareProvider;
        this.healthData = healthData;
        this.carePlans = carePlans;
        this.reports = reports;
        this.appointments = appointments;
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

    @Override
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

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HealthCareProvider getHealthCareProvider() {
        return healthCareProvider;
    }

    public void setHealthCareProvider(HealthCareProvider healthCareProvider) {
        this.healthCareProvider = healthCareProvider;
    }

    public List<HealthData> getHealthData() {
        return healthData;
    }

    public void setHealthData(List<HealthData> healthData) {
        this.healthData = healthData;
    }

    public CarePlan getCarePlans() {
        return carePlans;
    }

    public void setCarePlans(CarePlan carePlans) {
        this.carePlans = carePlans;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String getUsername() {
        return this.email;
    }


}
