package finalProject.service;

import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;
import finalProject.domain.Report;
import finalProject.repository.PatientRepo;
import finalProject.repository.ProviderRepo;
import finalProject.repository.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ReportRepo reportRepo;
    private  final PatientRepo patientRepo;
    private final ProviderRepo providerRepo;
    @Autowired
    public ReportService(ReportRepo reportRepo, PatientRepo patientRepo, ProviderRepo providerRepo) {
        this.reportRepo = reportRepo;
        this.patientRepo = patientRepo;
        this.providerRepo = providerRepo;
    }


    public void saveReport(Report report){
        if(report!=null){
            reportRepo.save(report);

        }
    }
    public List<Report>allReports(){
        return reportRepo.findAll();
    }
    public Report findReport(int id){
        return reportRepo.findById(id).orElse(null);
    }
    public List<Report>findByPatient(int patientId){
        Patient patient=patientRepo.findById(patientId).orElse(null);
        if(patient!=null){
            return reportRepo.findByPatient(patient);
        }
        return null;
    }
    List<Report>findByProvider(int providerId){
        HealthCareProvider provider=providerRepo.findById(providerId).orElse(null);
        if(provider!=null){
            return  reportRepo.findByHealthCareProvider(provider);
        }
        return null;
    }
    public boolean deleteReport(int id){
        Report report=reportRepo.findById(id).orElse(null);
        if(report!=null) {
            reportRepo.delete(report);
            return true;
        }
        return false;
    }
}
