package finalProject.controller;

import finalProject.domain.HealthData;
import finalProject.domain.Patient;
import finalProject.dto.HealthDataDto;
import finalProject.service.HealthDataService;
import finalProject.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/healthData")
public class HealthDataController {
    private final HealthDataService  healthDataService;
    private final PatientService patientService;
    @Autowired
    public HealthDataController(HealthDataService healthDataService, PatientService patientService) {
        this.healthDataService = healthDataService;
        this.patientService = patientService;
    }
    @PostMapping("/saveData")
    @PreAuthorize("hasAuthority('patient')")
    public ResponseEntity<?>saveData(@RequestBody HealthDataDto healthDataDto){
        Patient patient=patientService.findPatient(healthDataDto.getPatientId());
        if(patient==null){
            return ResponseEntity.badRequest().body("Invalid patient ID");
        }
        HealthData data=new HealthData();
        data.setCalories(healthDataDto.getCalories());
        data.setBodyWater(healthDataDto.getBodyWater());
        data.setExercisesDuration(healthDataDto.getExercisesDuration());
        data.setHeartRate(healthDataDto.getHeartRate());
        data.setBloodGlucose(healthDataDto.getBloodGlucose());
        data.setBloodPressure(healthDataDto.getBloodPressure());
        data.setRespLevel(healthDataDto.getRespLevel());
        data.setStressLevel(healthDataDto.getStressLevel());
        data.setPatient(patient);
        healthDataService.saveData(data);
        return new ResponseEntity<>("health data saved", HttpStatus.OK);
    }
    @GetMapping("/allData")
    @PreAuthorize("hasAuthority('healthcare')")
    public ResponseEntity<?>allData(){
        List<HealthData>allHealthData=healthDataService.allData();
        if(allHealthData!=null){
            List<HealthDataDto>dataDtoList=new ArrayList<>();
            for(HealthData data:allHealthData){
                HealthDataDto dataDto=new HealthDataDto();
                dataDto.setDataId(data.getId());
                dataDto.setPatientId(data.getPatient().getPatientId());
                dataDto.setNames(data.getPatient().getNames());
                dataDto.setSickness(data.getPatient().getSickness());
                dataDto.setCalories(data.getCalories());
                dataDto.setBodyWater(data.getBodyWater());
                dataDto.setExercisesDuration(data.getExercisesDuration());
                dataDto.setHeartRate(data.getHeartRate());
                dataDto.setBloodPressure(data.getBloodPressure());
                dataDto.setBloodGlucose(data.getBloodGlucose());
                dataDto.setRespLevel(data.getRespLevel());
                dataDto.setStressLevel(data.getStressLevel());
                dataDto.setRegDate(data.getRegDate());
                dataDtoList.add(dataDto);

            }
            return new ResponseEntity<>(dataDtoList,HttpStatus.OK);

        }
        return new ResponseEntity<>("No data found",HttpStatus.BAD_REQUEST);
    }
@GetMapping("/findData/{id}")
@PreAuthorize("hasAuthority('healthcare')")
    public ResponseEntity<?>findData(@PathVariable("id") int id){
        HealthData data=healthDataService.findData(id);
        if(data!=null){
            HealthDataDto dataDto=new HealthDataDto();
            dataDto.setDataId(data.getId());
            dataDto.setPatientId(data.getPatient().getPatientId());
            dataDto.setNames(data.getPatient().getNames());
            dataDto.setSickness(data.getPatient().getSickness());
            dataDto.setCalories(data.getCalories());
            dataDto.setBodyWater(data.getBodyWater());
            dataDto.setExercisesDuration(data.getExercisesDuration());
            dataDto.setHeartRate(data.getHeartRate());
            dataDto.setBloodPressure(data.getBloodPressure());
            dataDto.setBloodGlucose(data.getBloodGlucose());
            dataDto.setRespLevel(data.getRespLevel());
            dataDto.setStressLevel(data.getStressLevel());
            dataDto.setRegDate(data.getRegDate());
            return new ResponseEntity<>(dataDto,HttpStatus.OK);

        }
        return new ResponseEntity<>("no data found",HttpStatus.OK);
    }
    @GetMapping("/findPatientData/{patientId}")
    @PreAuthorize("hasAuthority('healthcare')")
    public ResponseEntity<?>findDataByPatient(@PathVariable ("patientId") int patientId) {
        List<HealthData> dataList = healthDataService.findByPatient(patientId);
        if (dataList != null) {
            List<HealthDataDto>dataDtoList=new ArrayList<>();
            for(HealthData data:dataList){
                HealthDataDto dataDto=new HealthDataDto();
                dataDto.setDataId(data.getId());
                dataDto.setPatientId(data.getPatient().getPatientId());
                dataDto.setNames(data.getPatient().getNames());
                dataDto.setSickness(data.getPatient().getSickness());
                dataDto.setCalories(data.getCalories());
                dataDto.setBodyWater(data.getBodyWater());
                dataDto.setExercisesDuration(data.getExercisesDuration());
                dataDto.setHeartRate(data.getHeartRate());
                dataDto.setBloodPressure(data.getBloodPressure());
                dataDto.setBloodGlucose(data.getBloodGlucose());
                dataDto.setRespLevel(data.getRespLevel());
                dataDto.setStressLevel(data.getStressLevel());
                dataDto.setRegDate(data.getRegDate());
                dataDtoList.add(dataDto);
            }
            return new ResponseEntity<>(dataDtoList,HttpStatus.OK);
        }
        return new ResponseEntity<>("No data found", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/findDataByPatientAndDate/{patientId}/{date}")
    @PreAuthorize("hasAuthority('healthcare')")
    public ResponseEntity<?>findDataByResponse(@PathVariable ("patientId") int patientId, @PathVariable("date") String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        HealthData data = healthDataService.findByPatientAndDate(patientId,localDate);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>("No data found", HttpStatus.BAD_REQUEST);
    }

}
