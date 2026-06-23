package sindplus.api.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import sindplus.api.model.comunicado.Comunicado;
import sindplus.api.model.comunicado.ComunicadoRepository;
import sindplus.api.model.comunicado.DadosListagemComunicado;
import sindplus.api.model.comunicado.DadosRegistroComunicado;
import sindplus.api.model.condominio.Condominio;
import sindplus.api.model.condominio.CondominioRepository;

@RestController
@RequestMapping("comunicados")
public class ComunicadoController {

    @Autowired
    private ComunicadoRepository comunicadoRepository;
    @Autowired
    private CondominioRepository condoRepository;


    @PostMapping
    @Transactional
    public void registrar(@RequestBody DadosRegistroComunicado dados) {
        Condominio condominio = condoRepository.getReferenceById(dados.condominioId());
        Comunicado comunicado = new Comunicado(dados);
        comunicado.setCondominio(condominio);
        comunicado.setData(LocalDateTime.now());
        comunicadoRepository.save(comunicado);
    }

    @GetMapping({"listar/{id}"})
    public List<DadosListagemComunicado> listarPorCondominio(@PathVariable Integer id) {
        return comunicadoRepository.findByCondominioId(id).stream().map(DadosListagemComunicado::new).toList();
    }
}



