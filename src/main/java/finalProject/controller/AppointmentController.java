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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final EmailService emailService;
    private final PatientService patientService;
    private final ProviderService providerService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, EmailService emailService, PatientService patientService, ProviderService providerService) {
        this.appointmentService = appointmentService;
        this.emailService = emailService;
        this.patientService = patientService;
        this.providerService = providerService;
    }
    @PostMapping("/bookAppointment")
    @PreAuthorize("hasAuthority('patient')")
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
            appointment.setRequestDate(dto.getRequestDate());
            appointment.setPatient(patient);
            appointment.setHealthCareProvider(provider);
            appointmentService.saveAppointment(appointment);
            emailService.sendingEmails(email,subject,text);
            return new ResponseEntity<>("Appointment Booked", HttpStatus.OK);
        }
        return new ResponseEntity<>("Appointment not booed",HttpStatus.BAD_REQUEST);
    }
@GetMapping("/allAppointment")
@PreAuthorize("hasAuthority('admin')")
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

            }
            return new ResponseEntity<>(appointmentDtoList,HttpStatus.OK);
        }
        return new ResponseEntity<>("error fetching appointments",HttpStatus.BAD_REQUEST);
    }
   @GetMapping("/findAppointment/{id}")
   @PreAuthorize("hasAnyAuthority('admin','healthcare','patient')")
    public  ResponseEntity<?>findAppointment(@PathVariable("id") int id){
        Appointment appointment=appointmentService.findAppointment(id);
        if(appointment!=null){
            AppointmentDto dto=new AppointmentDto();
            dto.setAppointmentId(appointment.getAppointmentId());
            dto.setType(appointment.getType());
            dto.setStatus(appointment.getStatus());
            dto.setRequestDate(appointment.getRequestDate());
            dto.setPatientId(appointment.getPatient().getPatientId());
            dto.setNames(appointment.getPatient().getNames());
            dto.setEmail(appointment.getPatient().getEmail());
            dto.setProviderId(appointment.getHealthCareProvider().getProviderId());
            dto.setProviderNames(appointment.getHealthCareProvider().getNames());
            return new ResponseEntity<>(appointment,HttpStatus.OK);
        }
        return new ResponseEntity<>("no appointment found",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/findProviderAppointment/{id}")
    @PreAuthorize("hasAuthority('healthcare')")
    public ResponseEntity<?>findProviderAppointments(@PathVariable("id") int id){
        List<Appointment>appointmentList=appointmentService.findByProvider(id);
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
                dto.setEmail(appointment.getPatient().getEmail());
                dto.setProviderId(appointment.getHealthCareProvider().getProviderId());
                dto.setProviderNames(appointment.getHealthCareProvider().getNames());
                appointmentDtoList.add(dto);
            }
            return new ResponseEntity<>(appointmentDtoList,HttpStatus.OK);
        }
        return new ResponseEntity<>("error fetching appointments",HttpStatus.BAD_REQUEST);
        }
        @PostMapping("/approveAppointment/{id}")
        @PreAuthorize("hasAuthority('healthcare')")
        public ResponseEntity<?>updateAppointment(@PathVariable ("id") int id,  @RequestBody Map<String, String> requestBody){
          Appointment appointment=appointmentService.findAppointment(id);
          Patient patient=patientService.findPatient(appointment.getPatient().getPatientId());
          if(patient != null){
              String email= patient.getEmail();
              String subject="Appointment Approved";
              String feedback=requestBody.get("feedback");
              appointmentService.updateAppointment(id,"approved");
              emailService.sendingEmails(email,subject,feedback);
              return new ResponseEntity<>("appointment Approved",HttpStatus.OK);
          }
          return new ResponseEntity<>("Failed to approve appointment",HttpStatus.BAD_REQUEST);
        }
    @PostMapping("/declineAppointment/{id}")
    @PreAuthorize("hasAuthority('healthcare')")
    public ResponseEntity<?>declineAppointment(@PathVariable ("id") int id,  @RequestBody Map<String, String> requestBody){
        Appointment appointment=appointmentService.findAppointment(id);
        Patient patient=patientService.findPatient(appointment.getPatient().getPatientId());
        if(patient != null){
            String email= patient.getEmail();
            String subject="Appointment Declined";
            String feedback=requestBody.get("feedback");
            appointmentService.updateAppointment(id,"declined");
            emailService.sendingEmails(email,subject,feedback);
            return new ResponseEntity<>("appointment Declined",HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to decline appointment",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/deleteAppointment/{id}")
    @PreAuthorize("hasAuthority('healthcare')")
    public ResponseEntity<?>deleteAppointment(@PathVariable("id") int id) {
        boolean isDeleted = appointmentService.deleteAppointment(id);
        if (isDeleted) {
            return new ResponseEntity<>("appointment Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("fail to delete appointment",HttpStatus.BAD_REQUEST);
    }


}


















