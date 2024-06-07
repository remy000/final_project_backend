package finalProject.service;

import finalProject.domain.HealthCareProvider;
import finalProject.repository.ProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    private final ProviderRepo providerRepo;
    private final PasswordEncoder passwordEncoder;
@Autowired
    public ProviderService(ProviderRepo providerRepo, PasswordEncoder passwordEncoder) {
        this.providerRepo = providerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveProvider(HealthCareProvider provider){
        if (provider != null) {
            provider.setRoles("healthcare");
            provider.setPassword(passwordEncoder.encode(provider.getPassword()));
            providerRepo.save(provider);
        }
    }
    public List<HealthCareProvider>allProviders(){
       return providerRepo.findAll();
    }
    public HealthCareProvider findCareProvider(int id){
       return providerRepo.findById(id).orElse(null);
    }
    public HealthCareProvider findProviderByEmail(String email){
       return providerRepo.findByEmail(email).orElse(null);
    }
    public boolean deleteProvider(int id){
       HealthCareProvider provider=providerRepo.findById(id).orElse(null);
       if(provider!=null){
           providerRepo.delete(provider);
           return true;
       }
       return false;
    }

    public void updateProvider(HealthCareProvider provider){
       providerRepo.findById(provider.getProviderId()).map(careProvider->{
           careProvider.setNames(provider.getNames());
           careProvider.setEmail(provider.getEmail());
           careProvider.setPhoneNumber(provider.getPhoneNumber());
           careProvider.setGender(provider.getGender());
           careProvider.setAddress(provider.getAddress());
           careProvider.setQualifications(provider.getQualifications());
           careProvider.setExperience(provider.getExperience());
           careProvider.setSpecialization(provider.getSpecialization());
           return providerRepo.save(careProvider);
       })
               .orElseThrow(()->new RuntimeException("No care provider found with"+provider.getProviderId()));
    }




}
