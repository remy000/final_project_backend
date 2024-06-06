package finalProject.service;

import finalProject.domain.Appointment;
import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;
import finalProject.repository.AppointmentRepo;
import finalProject.repository.PatientRepo;
import finalProject.repository.ProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final PatientRepo patientRepo;
    private final ProviderRepo providerRepo;
@Autowired
    public AppointmentService(AppointmentRepo appointmentRepo, PatientRepo patientRepo, ProviderRepo providerRepo) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.providerRepo = providerRepo;
    }

    public void saveAppointment(Appointment appointment){
    if (appointment!=null){
        appointmentRepo.save(appointment);
    }
    }
    public List<Appointment>allAppointments(){
    return appointmentRepo.findAll();
    }
    public Appointment findAppointment(int id){
    return appointmentRepo.findById(id).orElse(null);
    }
    public List<Appointment>findByPatient(int id){
        Patient patient=patientRepo.findById(id).orElse(null);
        if(patient!=null){
             return appointmentRepo.findByPatient(patient);
        }
        return null;
    }
    public List<Appointment>findByProvider(int id){
        HealthCareProvider provider=providerRepo.findById(id).orElse(null);
        if(provider!=null){
            return appointmentRepo.findByHealthCareProvider(provider);
        }
        return null;

    }

    public void updateAppointment(int id, String status){
    Appointment appointment=appointmentRepo.findById(id).orElse(null);
    if(appointment!=null){
        appointment.setStatus(status);
        appointmentRepo.save(appointment);
    }

    }
    public boolean deleteAppointment(int id){
    Appointment appointment=appointmentRepo.findById(id).orElse(null);
    if(appointment!=null){
        appointmentRepo.delete(appointment);
        return true;
    }
         return false;
    }

}
