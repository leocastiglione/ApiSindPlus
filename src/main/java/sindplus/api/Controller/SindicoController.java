package sindplus.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import sindplus.api.model.sindico.DadosAtualizacaoSindico;
import sindplus.api.model.sindico.DadosCadastroSindico;
import sindplus.api.model.sindico.Sindico;
import sindplus.api.model.sindico.SindicoRepository;


@RestController
@RequestMapping("/sindicos")
public class SindicoController {
    @Autowired
    private SindicoRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastro")
    @Transactional
    public void cadastrarSindico(@RequestBody DadosCadastroSindico dados) {
        Sindico sindico = new Sindico();
        sindico.setNome(dados.nome());
        sindico.setEmail(dados.email());
        sindico.setPerfil(dados.perfil());
        sindico.setSenha(passwordEncoder.encode(dados.senha()));
        repository.save(sindico);
    }

    @GetMapping("id/{id}")
    public Sindico buscarPorId(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping
    public Sindico buscarPorEmail(@RequestParam String email) {
        return repository.findByEmail(email).orElse(null);
    }


    @PutMapping("atualizar")
    @Transactional
    public void atualizar(@RequestBody DadosAtualizacaoSindico dados) {
        Sindico sindico = repository.getReferenceById(dados.id());
        sindico.alterarDados(dados);
    }

    @DeleteMapping("alterar-status/{id}")
    @Transactional
    public void alterarStatus(@PathVariable Integer id) {
        Sindico sindico = repository.getReferenceById(id);
        sindico.exclusaoLogica();
    }
}
