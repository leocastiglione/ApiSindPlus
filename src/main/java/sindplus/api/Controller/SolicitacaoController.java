package sindplus.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import sindplus.api.model.morador.Morador;
import sindplus.api.model.morador.MoradorRepository;
import sindplus.api.model.solicitacao.DadosListagemSolicitacao;
import sindplus.api.model.solicitacao.DadosRegistroSolicitacao;
import sindplus.api.model.solicitacao.Solicitacao;
import sindplus.api.model.solicitacao.SolicitacaoRepository;
import sindplus.api.model.solicitacao.Status;

@RestController
@RequestMapping("solicitacoes")

public class SolicitacaoController {
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@PostMapping
	@Transactional
	public void criarsolicitacao (@RequestBody DadosRegistroSolicitacao dados) {
		
		Morador morador = moradorRepository.getReferenceById(dados.moradorId());
		Solicitacao solicitacao = new Solicitacao(dados);
		solicitacao.setMorador(morador);
		solicitacao.setCondominio(morador.getCondominio());
		solicitacao.setStatus(Status.ABERTO);
		solicitacao.setCategoria(dados.categoria());
		solicitacaoRepository.save(solicitacao);
	}

    @GetMapping("listarCondominioId/{id}")
    public List<DadosListagemSolicitacao> listarPorCondominio(@PathVariable Integer id) {
        return solicitacaoRepository.findByCondominioId(id).stream().map(DadosListagemSolicitacao::new).toList();
    }


    @GetMapping("listarMoradorId/{id}")
    public List<DadosListagemSolicitacao> listarPorMorador(@PathVariable Integer id) {
        return solicitacaoRepository.findByMoradorId(id).stream().map(DadosListagemSolicitacao::new).toList();
    }

    @GetMapping("listarSolicitacaoPorCondominioSindicoIgual")
    public List<DadosListagemSolicitacao> listarSolicitacaoPorCondominioSindicoIgual(@RequestParam List<Integer> condominioId) {
        return solicitacaoRepository.findByCondominioIdIn(condominioId).stream().map(DadosListagemSolicitacao::new).toList();
    }


}
