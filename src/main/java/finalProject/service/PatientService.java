package finalProject.service;

import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;
import finalProject.repository.PatientRepo;
import finalProject.repository.ProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepo patientRepo;
    private final ProviderRepo providerRepo;
    private final PasswordEncoder passwordEncoder;
@Autowired
    public PatientService(PatientRepo patientRepo, ProviderRepo providerRepo, PasswordEncoder passwordEncoder) {
        this.patientRepo = patientRepo;
        this.providerRepo = providerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void savePatient(Patient patient){
        if(patient!=null){
            patient.setRoles("patient");
            patient.setAssignedProvider("no");
            patient.setPassword(passwordEncoder.encode(patient.getPassword()));
            patient.setHealthCareProvider(null);
            patientRepo.save(patient);
        }
    }
    public List<Patient>allPatients(){
        return patientRepo.findAll();
    }
    public Patient findPatient(int id){
        return patientRepo.findById(id).orElse(null);
    }
    public boolean deletePatient(int id){
        Patient patient=patientRepo.findById(id).orElse(null);
        if(patient!=null){
            patientRepo.delete(patient);
            return true;
        }
        return false;
    }
    public List<Patient>findByHealthProviders(int id){
        HealthCareProvider provider=providerRepo.findById(id).orElse(null);
        if(provider!=null){
            return patientRepo.findByHealthCareProvider(provider);
        }
        return null;
    }
    public void updatePatient(Patient updatedPatient){
        Patient patient=patientRepo.findById(updatedPatient.getPatientId()).orElse(null);
        if(patient!=null){
            patient.setNames(updatedPatient.getNames());
            patient.setEmail(updatedPatient.getEmail());
            patient.setPhoneNumber(updatedPatient.getPhoneNumber());
            patient.setBloodGroup(updatedPatient.getBloodGroup());
            patient.setBirthDate(updatedPatient.getBirthDate());
            patient.setWeight(updatedPatient.getWeight());
            patient.setGender(updatedPatient.getGender());
            patient.setAge(updatedPatient.getAge());
            patient.setAddress(updatedPatient.getAddress());
            patient.setSickness(updatedPatient.getSickness());
            patient.setAllergies(updatedPatient.getAllergies());
            patientRepo.save(patient);

        }
    }
    public Patient findByEmails(String email){
        return patientRepo.findByEmail(email).orElse(null);
    }

    public void assignHealthProvider(int patientId, int providerId){
        Patient patient=patientRepo.findById(patientId).orElse(null);
        HealthCareProvider provider=providerRepo.findById(providerId).orElse(null);
        if(patient!=null && provider!=null){
            patient.setHealthCareProvider(provider);
            patient.setAssignedProvider("yes");
            patientRepo.save(patient);
        }
        else{
            throw new RuntimeException("Patient or HealthCareProvider not found");
        }
    }



}
