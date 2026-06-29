package sindplus.api.model.sindico;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SindicoRepository extends JpaRepository<Sindico, Integer> {
	
    Optional<Sindico> findByEmail(String email);

}
