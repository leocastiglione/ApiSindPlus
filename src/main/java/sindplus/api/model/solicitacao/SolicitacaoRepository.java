package sindplus.api.model.solicitacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer>{
	List<Solicitacao> findByCondominioId(Integer condominioId);

    List<Solicitacao> findByMoradorId(Integer moradorId);

    List<Solicitacao> findByCondominioIdIn(List<Integer> ids);

}
