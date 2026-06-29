package sindplus.api.model.comunicado;

import java.time.LocalDateTime;

import sindplus.api.model.condominio.Condominio;

public record DadosListagemComunicado(
		Integer id,		
		Condominio condominio,
		String tituloComunicado,
		String conteudoComunicado, 
		LocalDateTime data
		
) {
	public DadosListagemComunicado(Comunicado comunicado) {
		this(comunicado.getId(), comunicado.getCondominio(), comunicado.getTituloDoComunicado(),  comunicado.getConteudoDoComunicado(), comunicado.getData());
	}

}
