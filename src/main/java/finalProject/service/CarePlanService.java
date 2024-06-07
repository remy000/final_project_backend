package finalProject.service;

import finalProject.domain.CarePlan;
import finalProject.domain.Patient;
import finalProject.repository.CarePlanRepo;
import finalProject.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class CarePlanService {
    private final CarePlanRepo carePlanRepo;
    private final PatientRepo patientRepo;
    @Autowired
    public CarePlanService(CarePlanRepo carePlanRepo, PatientRepo patientRepo) {
        this.carePlanRepo = carePlanRepo;
        this.patientRepo = patientRepo;
    }
    public void saveCarePlan(CarePlan carePlan){
        if(carePlan!=null){
            carePlan.setCreationDate(LocalDate.now());
            carePlanRepo.save(carePlan);
        }
    }
    public List<CarePlan>allCarePlans(){
        return carePlanRepo.findAll();
    }
    public CarePlan findCarePlan(int id){
        return carePlanRepo.findById(id).orElse(null);
    }
    public boolean deleteCarePlan(int id){
        CarePlan care=carePlanRepo.findById(id).orElse(null);
        if(care!=null){
            carePlanRepo.delete(care);
            return true;
        }
        return false;
    }
    public CarePlan findByPatient(int id){
        Patient patient=patientRepo.findById(id).orElse(null);
        if(patient!=null){
            return carePlanRepo.findByPatient(patient).orElse(null);
        }
        return null;
    }
    public void  updateCarePlan(CarePlan updatedCarePlan){
        CarePlan existingPlan=carePlanRepo.findById(updatedCarePlan.getPlanId()).orElse(null);
        if(existingPlan!=null){
            existingPlan.setPersonalizedPlan(updatedCarePlan.getPersonalizedPlan());
            existingPlan.setMedicalPlan(updatedCarePlan.getMedicalPlan());
            existingPlan.setExercisePlan(updatedCarePlan.getExercisePlan());
            existingPlan.setDietPlan(updatedCarePlan.getDietPlan());
            existingPlan.setCreationDate(LocalDate.now());
            carePlanRepo.save(existingPlan);
        }
    }
}
