package finalProject.controller;

import finalProject.domain.CarePlan;
import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;
import finalProject.dto.CarePlanDto;
import finalProject.service.CarePlanService;
import finalProject.service.EmailService;
import finalProject.service.PatientService;
import finalProject.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carePlan")
public class CarePlanController {
    private final EmailService emailService;
    private final CarePlanService carePlanService;
    private final PatientService patientService;
    private final ProviderService providerService;
@Autowired
    public CarePlanController(EmailService emailService, CarePlanService carePlanService, PatientService patientService, ProviderService providerService) {
        this.emailService = emailService;
        this.carePlanService = carePlanService;
        this.patientService = patientService;
        this.providerService = providerService;
    }
    @PostMapping("/saveCarePlan")
    @PreAuthorize("hasAuthority('healthcare')")
    public ResponseEntity<?>saveCarePlan(@RequestBody CarePlanDto dto) {
        Patient patient = patientService.findPatient(dto.getPatientId());
        HealthCareProvider provider = providerService.findCareProvider(dto.getProviderId());
        if (patient != null && provider != null) {
            String email = patient.getEmail();
            String subject = "Patient Care Plan created";
            String text = "Dear " + patient.getNames() + ", your care plan has been created.\n" +
                    "You can access it through your mobile application.\n" +
                    "Please follow it carefully for your well being.";
            CarePlan plan = new CarePlan();
            plan.setPersonalizedPlan(dto.getPersonalizedPlan());
            plan.setMedicalPlan(dto.getMedicalPlan());
            plan.setExercisePlan(dto.getExercisePlan());
            plan.setDietPlan(dto.getDietPlan());
            plan.setPatient(patient);
            plan.setHealthCareProvider(provider);
            carePlanService.saveCarePlan(plan);
            emailService.sendingEmails(email,subject,text);
            return new ResponseEntity<>("Care Plan Saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("No User found",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/allPlans")
    @PreAuthorize("hasAuthority('healthcare')")
    public ResponseEntity<?>allCarePlan(){
        List<CarePlan>allPlans=carePlanService.allCarePlans();
        if(allPlans!=null){
            return new ResponseEntity<>(allPlans,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Plans Found",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/patientPlan/{id}")
    @PreAuthorize("hasAnyAuthority('healthcare','patient')")
    public ResponseEntity<?>findPatientPlan(@PathVariable ("id") int id){
      CarePlan plan=carePlanService.findByPatient(id);
      if(plan!=null){
          return new ResponseEntity<>(plan,HttpStatus.OK);
      }
      return new ResponseEntity<>("No Plan for patient",HttpStatus.BAD_REQUEST);
     }
     @GetMapping("/patientPlanByProvider/{patientId}/{providerId}")
     @PreAuthorize("hasAnyAuthority('healthcare','patient')")
     public ResponseEntity<?>findByPatientAndProvider(@PathVariable("patientId") int patId, @PathVariable("providerId")
                                                      int providerId){
     CarePlan plan=carePlanService.findByPatientAndProvider(patId, providerId);
     if(plan!=null){
         CarePlanDto dto=new CarePlanDto();
         dto.setPlanId(plan.getPlanId());
         dto.setPatientId(plan.getPatient().getPatientId());
         dto.setNames(plan.getPatient().getNames());
         dto.setSickness(plan.getPatient().getSickness());
         dto.setPersonalizedPlan(plan.getPersonalizedPlan());
         dto.setMedicalPlan(plan.getMedicalPlan());
         dto.setExercisePlan(plan.getExercisePlan());
         dto.setDietPlan(plan.getDietPlan());
         dto.setCreationDate(plan.getCreationDate());
         dto.setProviderId(plan.getHealthCareProvider().getProviderId());
         return new ResponseEntity<>(dto,HttpStatus.OK);
     }
     return new ResponseEntity<>("No Plan found",HttpStatus.BAD_REQUEST);
     }
     @GetMapping("/findPlan/{id}")
     @PreAuthorize("hasAnyAuthority('healthcare','patient')")
     public ResponseEntity<?>findPlan(@PathVariable("id") int id){
    CarePlan plan=carePlanService.findCarePlan(id);
    if(plan!=null){
        CarePlanDto dto=new CarePlanDto();
        dto.setPatientId(plan.getPatient().getPatientId());
        dto.setNames(plan.getPatient().getNames());
        dto.setProviderId(plan.getHealthCareProvider().getProviderId());
        dto.setPersonalizedPlan(plan.getPersonalizedPlan());
        dto.setMedicalPlan(plan.getMedicalPlan());
        dto.setExercisePlan(plan.getExercisePlan());
        dto.setDietPlan(plan.getDietPlan());
        dto.setCreationDate(plan.getCreationDate());
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
    return new ResponseEntity<>("Plan not found",HttpStatus.BAD_REQUEST);
     }
@DeleteMapping("/deletePlan/{id}")
@PreAuthorize("hasAuthority('healthcare')")
     public ResponseEntity<?>deletePlan(@PathVariable("id") int id){
      boolean isDeleted= carePlanService.deleteCarePlan(id);
      if(isDeleted){
          return new ResponseEntity<>("care plan deleted",HttpStatus.OK);
      }
         return new ResponseEntity<>("care  not plan deleted",HttpStatus.OK);
     }
     @PutMapping("/updatePlan")
     @PreAuthorize("hasAuthority('healthcare')")
     public ResponseEntity<?>updatePlan(@RequestBody CarePlan carePlan){
    carePlanService.updateCarePlan(carePlan);
    return new ResponseEntity<>("care plan updated",HttpStatus.OK);

     }
}
