package sindplus.api.model.morador;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoradorRepository extends JpaRepository<Morador, Integer> {
	List<Morador> findByCondominioIdIn(List<Integer> ids);
	
    Optional<Morador> findByEmail(String email);

}
