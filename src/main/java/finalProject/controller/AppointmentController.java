package finalProject.controller;

import finalProject.domain.Appointment;
import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;
import finalProject.dto.AppointmentDto;
import finalProject.service.AppointmentService;
import finalProject.service.EmailService;
import finalProject.service.PatientService;
import finalProject.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private AppointmentService appointmentService;
    private EmailService emailService;
    private PatientService patientService;
    private ProviderService providerService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, EmailService emailService, PatientService patientService, ProviderService providerService) {
        this.appointmentService = appointmentService;
        this.emailService = emailService;
        this.patientService = patientService;
        this.providerService = providerService;
    }
    @PostMapping("/bookAppointment")
    public ResponseEntity<?>bookAppointment(@RequestBody AppointmentDto dto){
        Patient patient=patientService.findPatient(dto.getPatientId());
        HealthCareProvider provider=providerService.findCareProvider(dto.getProviderId());
        if(patient!=null && provider!=null){
            String email= patient.getEmail();
            String subject = "Appointment Booked";
            String text = "Dear, "+ patient.getNames() +"  your Appointment has been booked successfully.\n" +
                    "Your healthCare provider will be back to you shortly.\n" +
                    "Thank you for your patience.";
            Appointment appointment=new Appointment();
            appointment.setType(dto.getType());
            appointment.setRequestDate(LocalDate.now());
            appointment.setPatient(patient);
            appointment.setHealthCareProvider(provider);
            appointmentService.saveAppointment(appointment);
            emailService.sendingEmails(email,subject,text);
            return new ResponseEntity<>("Appointment Booked", HttpStatus.OK);
        }
        return new ResponseEntity<>("Appointment not booed",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?>allAppointments(){
        List<Appointment>appointmentList=appointmentService.allAppointments();
        if(appointmentList!=null){
            List<AppointmentDto>appointmentDtoList=new ArrayList<>();
            for(Appointment appointment:appointmentList){
                AppointmentDto dto=new AppointmentDto();
                dto.setAppointmentId(appointment.getAppointmentId());
                dto.setType(appointment.getType());
                dto.setStatus(appointment.getStatus());
                dto.setRequestDate(appointment.getRequestDate());
                dto.setPatientId(appointment.getPatient().getPatientId());
                dto.setNames(appointment.getPatient().getNames());
                dto.setProviderId(appointment.getHealthCareProvider().getProviderId());
                dto.setProviderNames(appointment.getHealthCareProvider().getNames());
                appointmentDtoList.add(dto);
                return new ResponseEntity<>(appointmentDtoList,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("error fetching appointments",HttpStatus.BAD_REQUEST);
    }
}


















