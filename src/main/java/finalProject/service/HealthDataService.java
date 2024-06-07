package finalProject.service;

import finalProject.domain.HealthData;
import finalProject.domain.Patient;
import finalProject.repository.HealthDataRepo;
import finalProject.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HealthDataService {
    private final HealthDataRepo healthDataRepo;
    private final PatientRepo patientRepo;
@Autowired
    public HealthDataService(HealthDataRepo healthDataRepo, PatientRepo patientRepo) {
        this.healthDataRepo = healthDataRepo;
        this.patientRepo = patientRepo;
    }

    public void saveData(HealthData data){
        if(data!=null){
            data.setRegDate(LocalDate.now());
            healthDataRepo.save(data);
        }
    }
    public List<HealthData> allData(){
        return healthDataRepo.findAll();
    }
    public HealthData findData(int id){

    return healthDataRepo.findById(id).orElse(null);
    }
    public List<HealthData>findByPatient(int id){
        Patient patient=patientRepo.findById(id).orElse(null);
        if(patient!=null){
            return healthDataRepo.findByPatient(patient);
        }
        return null;
    }
    public boolean deleteData(int id){
    HealthData data=healthDataRepo.findById(id).orElse(null);
    if(data!=null){
        healthDataRepo.delete(data);
        return true;
    }
    return false;
    }
    public HealthData findByPatientAndDate(int patientId, LocalDate regDate){
    Patient patient=patientRepo.findById(patientId).orElse(null);
    if(patient!=null && regDate!=null) {
        return  healthDataRepo.findByPatientAndRegDate(patient,regDate).orElse(null);
    }
    else{
        throw new RuntimeException("patient with" +patientId+ "not found");
    }
    }
    public void updateData(HealthData updatedData){
    HealthData data=healthDataRepo.findById(updatedData.getId()).orElse(null);
    if(data!=null){
        data.setCalories(updatedData.getCalories());
        data.setBodyWater(updatedData.getBodyWater());
        data.setExercisesDuration(updatedData.getExercisesDuration());
        data.setHeartRate(updatedData.getHeartRate());
        data.setBloodPressure(updatedData.getBloodPressure());
        data.setRespLevel(updatedData.getRespLevel());
        data.setStressLevel(updatedData.getStressLevel());
        data.setRegDate(LocalDate.now());
        healthDataRepo.save(data);

    }
    }

}
