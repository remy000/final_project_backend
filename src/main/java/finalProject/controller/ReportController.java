package finalProject.controller;

import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;
import finalProject.domain.Report;
import finalProject.dto.ReportDto;
import finalProject.service.EmailService;
import finalProject.service.PatientService;
import finalProject.service.ProviderService;
import finalProject.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final PatientService patientService;
    private final ProviderService providerService;
    private final EmailService emailService;

    @Autowired
    public ReportController(ReportService reportService, PatientService patientService, ProviderService providerService, EmailService emailService) {
        this.reportService = reportService;
        this.patientService = patientService;
        this.providerService = providerService;
        this.emailService = emailService;
    }

    @PostMapping("/saveReport")
    @PreAuthorize("hasAuthority('healthcare')")
    public ResponseEntity<?>saveReport(@RequestBody ReportDto reportDto){
        Patient patient=patientService.findPatient(reportDto.getPatientId());
        HealthCareProvider provider=providerService.findCareProvider(reportDto.getProviderId());
        if(patient!=null && provider!=null){
            String email=patient.getEmail();
            Report report=new Report();
            report.setTitle(reportDto.getTitle());
            report.setRecommendations(reportDto.getRecommendations());
            report.setImprovements(reportDto.getImprovements());
            report.setPatient(patient);
            report.setHealthCareProvider(provider);
            if(email!=null){
                String subject="New Report: "+reportDto.getTitle();
                String text = "Improvements: " + reportDto.getImprovements() + "\nRecommendations: " + reportDto.getRecommendations();
                reportService.saveReport(report);
                emailService.sendingEmails(email,subject,text);
            }

            return new ResponseEntity<>("report sent", HttpStatus.OK);
        }
        return new ResponseEntity<>("fail to send report",HttpStatus.BAD_REQUEST);

}
@GetMapping("/allReports")
@PreAuthorize("hasAnyAuthority('healthcare','admin','patient')")
    public ResponseEntity<?>allReports(){
        List<Report>allReports=reportService.allReports();
        if(allReports!=null){
            return new ResponseEntity<>(allReports,HttpStatus.OK);
        }
        return new ResponseEntity<>("no reports available",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("patientReports/{id}")
    @PreAuthorize("hasAnyAuthority('healthcare')")
    public ResponseEntity<?>patientReports(@PathVariable("id") int id){
    List<Report>reportList=reportService.findByPatient(id);
    if(reportList!=null){
        List<ReportDto>dtoList=new ArrayList<>();
        for(Report report:reportList){
            ReportDto dto=new ReportDto();
            dto.setReportId(report.getReportId());
            dto.setTitle(report.getTitle());
            dto.setRecommendations(report.getRecommendations());
            dto.setImprovements(report.getImprovements());
            dto.setReportDate(report.getReportDate());
            dto.setPatientId(report.getPatient().getPatientId());
            dto.setPatientNames(report.getPatient().getNames());
            dto.setProviderId(report.getHealthCareProvider().getProviderId());
            dtoList.add(dto);
        }
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }
    return new ResponseEntity<>("no report available",HttpStatus.BAD_REQUEST);
    }
@GetMapping("/findReport/{id}")
@PreAuthorize("hasAnyAuthority('healthcare','patient')")
    public ResponseEntity<?>findReport(@PathVariable("id") int id){
        Report report=reportService.findReport(id);
        if(report!=null){
            ReportDto dto=new ReportDto();
            dto.setReportId(report.getReportId());
            dto.setTitle(report.getTitle());
            dto.setRecommendations(report.getRecommendations());
            dto.setReportDate(report.getReportDate());
            dto.setPatientId(report.getPatient().getPatientId());
            dto.setPatientNames(report.getPatient().getNames());
            dto.setProviderId(report.getHealthCareProvider().getProviderId());
            return new ResponseEntity<>(dto,HttpStatus.OK);

        }
        return new ResponseEntity<>("no report found",HttpStatus.BAD_REQUEST);
}
@GetMapping("/providerReports/{id}")
@PreAuthorize("hasAuthority('healthcare')")
public ResponseEntity<?>providerAppointments(@PathVariable("id") int id){
    List<Report>allReports=reportService.findByProvider(id);
    if(allReports!=null){
        List<ReportDto>dtoList=new ArrayList<>();
        for(Report report:allReports){
            ReportDto dto=new ReportDto();
            dto.setReportId(report.getReportId());
            dto.setTitle(report.getTitle());
            dto.setRecommendations(report.getRecommendations());
            dto.setImprovements(report.getImprovements());
            dto.setReportDate(report.getReportDate());
            dto.setPatientId(report.getPatient().getPatientId());
            dto.setPatientNames(report.getPatient().getNames());
            dto.setProviderId(report.getHealthCareProvider().getProviderId());
            dtoList.add(dto);
        }
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }
    return new ResponseEntity<>("no report found",HttpStatus.BAD_REQUEST);
}
@DeleteMapping("/deleteReport/{id}")
public ResponseEntity<?>deleteReport(@PathVariable("id") int id){
    boolean isDeleted= reportService.deleteReport(id);
    if(isDeleted){
        return new ResponseEntity<>("report deleted",HttpStatus.OK);
    }
    return new ResponseEntity<>("fail to delete report",HttpStatus.BAD_REQUEST);

}












}
