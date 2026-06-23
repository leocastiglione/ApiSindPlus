package sindplus.api.model.comunicado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComunicadoRepository extends JpaRepository<Comunicado, Integer> {
	List<Comunicado> findByCondominioId(Integer condominioId);
	
}
