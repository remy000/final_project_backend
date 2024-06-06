package finalProject.repository;

import finalProject.domain.HealthCareProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepo extends JpaRepository<HealthCareProvider,Integer> {
    Optional<HealthCareProvider>findByEmail(String email);
}
