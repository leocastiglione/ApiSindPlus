package sindplus.api.model.morador;

import sindplus.api.model.condominio.Condominio;

public record DadosAtualizacaoMorador(
		Integer id, 
		String nome, 
		String email, 
		String unidade,
		Condominio condominio
) {

}
