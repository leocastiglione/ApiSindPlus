package sindplus.api.model.condominio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CondominioRepository extends JpaRepository<Condominio, Integer> {
	List<Condominio> findBySindicoId(Integer sindicoId);

}
