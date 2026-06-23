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
import sindplus.api.model.morador.DadosAtualizacaoMorador;
import sindplus.api.model.morador.DadosCadastroMorador;
import sindplus.api.model.morador.DadosListagemMorador;
import sindplus.api.model.morador.Morador;
import sindplus.api.model.morador.MoradorRepository;

@RestController
@RequestMapping("moradores")
public class MoradorController {
	

    @Autowired
    private MoradorRepository moradorRepository;
    @Autowired
    private CondominioRepository condoRepository;

    public MoradorController() {
    }

    @PostMapping({"/cadastro"})
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroMorador dados) {
        Condominio condominio = condoRepository.getReferenceById(dados.condominioId());
        Morador morador = new Morador(dados);
        morador.setCondominio(condominio);
        moradorRepository.save(morador);
    }

    @GetMapping({"listar"})
    public List<DadosListagemMorador> listar() {
        return moradorRepository.findAll().stream().map(DadosListagemMorador::new).toList();
    }

    @GetMapping({"listarporcondominioid"})
    public List<DadosListagemMorador> listarPorCondominioId(@RequestParam List<Integer> condominioId) {
        return moradorRepository.findByCondominioIdIn(condominioId).stream().map(DadosListagemMorador::new).toList();
    }

    @PutMapping({"atualizar"})
    @Transactional
    public void atualizar(@RequestBody DadosAtualizacaoMorador dados) {
        Morador morador = moradorRepository.getReferenceById(dados.id());
        morador.alterarDados(dados);
    }

    @DeleteMapping({"alterar-status/{id}"})
    @Transactional
    public void alterarStatus(@PathVariable Integer id) {
        Morador morador = moradorRepository.getReferenceById(id);
        morador.exclusaoLogica();
    }

}
