package finalProject.repository;

import finalProject.domain.HealthCareProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepo extends JpaRepository<HealthCareProvider,Integer> {
}
