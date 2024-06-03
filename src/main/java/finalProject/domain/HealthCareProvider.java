package finalProject.domain;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table
public class HealthCareProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "provider_generator")
    @SequenceGenerator(
            name = "provider_generator",
            sequenceName = "provider_sequence_name",
            allocationSize = 1
    )
    private int providerId;
    private String names;
    private String email;
    private String phoneNumber;
    private String gender;
    private String address;
    private String qualifications;
    private String experience;
    private String roles;
    private String specialization;
    private String password;
    @OneToMany(mappedBy = "healthCareProvider")
    private List<Patient>patients;
    @OneToMany(mappedBy = "healthCareProvider")
    private List<Appointment>appointments;
    @OneToMany(mappedBy = "healthCareProvider")
    private List<CarePlan>carePlans;
    @OneToMany(mappedBy = "healthCareProvider")
    private List<Report>reports;

    public HealthCareProvider() {
    }

    public HealthCareProvider(int providerId, String names, String email, String phoneNumber, String gender, String address, String qualifications, String experience, String roles, String specialization, String password, List<Patient> patients, List<Appointment> appointments, List<CarePlan> carePlans, List<Report> reports) {
        this.providerId = providerId;
        this.names = names;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.qualifications = qualifications;
        this.experience = experience;
        this.roles = roles;
        this.specialization = specialization;
        this.password = password;
        this.patients = patients;
        this.appointments = appointments;
        this.carePlans = carePlans;
        this.reports = reports;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<CarePlan> getCarePlans() {
        return carePlans;
    }

    public void setCarePlans(List<CarePlan> carePlans) {
        this.carePlans = carePlans;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
