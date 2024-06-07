package finalProject.controller;

import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;
import finalProject.service.JwtService;
import finalProject.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/provider")

public class ProviderController {
    private  final ProviderService providerService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
@Autowired
    public ProviderController(ProviderService providerService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.providerService = providerService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> savePatient(@RequestBody HealthCareProvider provider) {
        if (provider != null) {
            HealthCareProvider provider1=providerService.findCareProvider(provider.getProviderId());
            if (provider1 == null) {
                providerService.saveProvider(provider);

                return new ResponseEntity<>("careProvider Saved", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("careProvider already exist", HttpStatus.FOUND);
            }
        }
        return new ResponseEntity<>("careProvider is null", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value = "/authentication", consumes = "application/json")
    public ResponseEntity<?> authReqAndToken(@RequestBody HealthCareProvider provider){
        HealthCareProvider user=providerService.findProviderByEmail(provider.getEmail());
        if(user!=null) {
            String roles=user.getRoles();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(String.valueOf(provider.getEmail()),provider.getPassword()));
            if (authentication.isAuthenticated()) {

                String token = jwtService.generateToken(String.valueOf(provider.getEmail()),roles);
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
    @GetMapping("/allCareProviders")
    public ResponseEntity<?>allHealthCareProvider(){
        List<HealthCareProvider> allProviders=providerService.allProviders();
        if(allProviders!=null){
            return new ResponseEntity<>(allProviders,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Patient found",HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @GetMapping("/findProvider/{id}")
    public ResponseEntity<?>findProvider(@PathVariable("id") int id){
        HealthCareProvider provider=providerService.findCareProvider(id);
        if(provider!=null){
            return new ResponseEntity<>(provider,HttpStatus.OK);
        }
        return new ResponseEntity<>("no patient found",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/findProviderByEmail/{email}")
    public ResponseEntity<?>findProviderByEmail(@PathVariable("email") String email){
        HealthCareProvider provider=providerService.findProviderByEmail(email);
        if(provider!=null){
            return new ResponseEntity<>(provider,HttpStatus.OK);
        }
        return new ResponseEntity<>("provider not found",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping("/updateProvider")
    public ResponseEntity<?>UpdateProvider(@RequestBody HealthCareProvider provider){
        providerService.updateProvider(provider);
        return new ResponseEntity<>("careProvider updated successfully",HttpStatus.OK);
    }
}
