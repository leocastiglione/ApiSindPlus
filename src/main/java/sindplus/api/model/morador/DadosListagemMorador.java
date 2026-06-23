package sindplus.api.model.morador;

import sindplus.api.model.condominio.Condominio;

public record DadosListagemMorador(
		Integer id, 
		String nome, 
		String email, 
		String unidade, 
		Condominio condominio
) {
	public DadosListagemMorador(Morador morador) {
        this(morador.getId(), morador.getNome(), morador.getEmail(), morador.getUnidade(), morador.getCondominio());
    }

}
