package finalProject.controller;

import finalProject.domain.Patient;
import finalProject.service.EmailService;
import finalProject.service.JwtService;
import finalProject.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private  final PatientService patientService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailService emailService;
@Autowired
    public PatientController(PatientService patientService, AuthenticationManager authenticationManager, JwtService jwtService, EmailService emailService) {
        this.patientService = patientService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<?>savePatient(@RequestBody Patient patient) {
        if (patient != null) {
            Patient patient1 = patientService.findPatient(patient.getPatientId());
            if (patient1 == null) {
                String userEmail = patient.getEmail();
                String subject = "Patient Account Created";
                String text = """
                        Thank you for Joining health Guard,
                         your account has been created successfully\s
                         Enjoy our service""";
                if (userEmail != null) {
                    patientService.savePatient(patient);
                    emailService.sendingEmails(userEmail, subject, text);
                }
                return new ResponseEntity<>("patient Saved", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("patient already exist", HttpStatus.FOUND);
            }
        }
        return new ResponseEntity<>("patient is null", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value = "/authentication", consumes = "application/json")
    public ResponseEntity<?> authReqAndToken(@RequestBody Patient patient){
        Patient user=patientService.findByEmails(patient.getEmail());
        if(user!=null) {
            String roles=user.getRoles();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(String.valueOf(patient.getEmail()),patient.getPassword()));
            if (authentication.isAuthenticated()) {

                String token = jwtService.generateToken(String.valueOf(patient.getEmail()),roles);
                Map<String, String> response = new HashMap<>();
                response.put("token", token);

                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                throw new UsernameNotFoundException("no user found");
            }
        }
        else{
            return  new ResponseEntity<>("user is null",HttpStatus.BAD_REQUEST);
        }

    }
@GetMapping("/allPatients")
@PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?>allPatients(){
        List<Patient>allPatients=patientService.allPatients();
        if(allPatients!=null){
            return new ResponseEntity<>(allPatients,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Patient found",HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @GetMapping("/findPatient/{id}")
    @PreAuthorize("hasAnyAuthority('patient','healthcare','admin')")
    public ResponseEntity<?>findPatient(@PathVariable("id") int id){
    Patient patient=patientService.findPatient(id);
    if(patient!=null){
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }
    return new ResponseEntity<>("no patient found",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/patientByProvider/{id}")
    @PreAuthorize("hasAuthority('healthcare')")
    public ResponseEntity<?>findPatientByProvider(@PathVariable("id") int id){
    List<Patient>patientList=patientService.findByHealthProviders(id);
    if(patientList!=null){
        return  new ResponseEntity<>(patientList,HttpStatus.OK);
    }
    return  new ResponseEntity<>("no patients found",HttpStatus.INTERNAL_SERVER_ERROR);
}
@GetMapping("/findPatientByEmail/{email}")
@PreAuthorize("hasAnyAuthority('patient','healthcare')")
public ResponseEntity<?>findPatientByEmail(@PathVariable("email") String email){
    Patient patient=patientService.findByEmails(email);
    if(patient!=null){
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }
    return new ResponseEntity<>("patient not found",HttpStatus.INTERNAL_SERVER_ERROR);
}
    @PostMapping("/assignProvider/{patientId}/{providerId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> assignHealthProvider(@PathVariable int patientId, @PathVariable int providerId) {
        try {
            patientService.assignHealthProvider(patientId, providerId);
            return new ResponseEntity<>("Health provider assigned successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/updatePatient")
    @PreAuthorize("hasAnyAuthority('patient','healthcare','admin')")
    public ResponseEntity<?>UpdatePatient(@RequestBody Patient patient){
           patientService.updatePatient(patient);
           return new ResponseEntity<>("Patient updated successfully",HttpStatus.OK);
    }

}
