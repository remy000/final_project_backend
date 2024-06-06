package finalProject.config;

import finalProject.domain.HealthCareProvider;
import finalProject.domain.Patient;
import finalProject.repository.PatientRepo;
import finalProject.repository.ProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoDetailService implements UserDetailsService {
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private ProviderRepo providerRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepo.findByEmail(username).orElse(null);
        if (patient != null) {
            return new UserInfoDetails(patient);
        }

        HealthCareProvider careProvider = providerRepo.findByEmail(username).orElse(null);
        if (careProvider != null) {
            return new UserInfoDetails(careProvider);
        }

        throw new UsernameNotFoundException("User not found with email: " + username);
    }


}
