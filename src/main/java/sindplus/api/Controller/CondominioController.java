package sindplus.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import sindplus.api.model.condominio.Condominio;
import sindplus.api.model.condominio.CondominioRepository;
import sindplus.api.model.condominio.DadosAtualizacaoCondominio;
import sindplus.api.model.condominio.DadosCadastroCondominio;
import sindplus.api.model.condominio.DadosListagemCondominio;
import sindplus.api.model.sindico.Sindico;
import sindplus.api.model.sindico.SindicoRepository;

@RestController
@RequestMapping("/condominios")
public class CondominioController {
	
	@Autowired
    private CondominioRepository condoRepository;
    @Autowired
    private SindicoRepository sindicoRepository;

    @PostMapping({"/cadastro"})
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroCondominio dados) {
        Sindico sindico = sindicoRepository.getReferenceById(dados.sindicoId());
        Condominio condominio = new Condominio(dados);
        condominio.setSindico(sindico);
        condoRepository.save(condominio);
    }

    @GetMapping({"/listar"})
    public List<DadosListagemCondominio> listar() {
        return condoRepository.findAll().stream().map(DadosListagemCondominio::new).toList();
    }

    @GetMapping
    public List<DadosListagemCondominio> listarPorSindicoId(@RequestParam Integer sindicoId) {
        return condoRepository.findBySindicoId(sindicoId).stream().map(DadosListagemCondominio::new).toList();
    }

    @PutMapping({"atualizar"})
    @Transactional
    public void atualizar(@RequestBody DadosAtualizacaoCondominio dados) {
        Condominio condominio = (Condominio)this.condoRepository.getReferenceById(dados.id());
        condominio.alterarDados(dados);
    }

    @DeleteMapping({"alterar-status/{id}"})    
    @Transactional
    public void alterarStatus(@PathVariable Integer id) {
        Condominio condominio = (Condominio)this.condoRepository.getReferenceById(id);
        condominio.exclusaoLogica();
    }

}
