package sindplus.api.model.condominio;

import sindplus.api.model.sindico.Sindico;

public record DadosListagemCondominio(Integer id, String nome, String cnpj, String endereco, Sindico sindico) {
	public DadosListagemCondominio(Condominio condominio) {
        this(condominio.getId(), condominio.getNome(), condominio.getCnpj(), condominio.getEndereco(), condominio.getSindico());}

}
