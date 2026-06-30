package sindplus.api.model.sindico;

public record DadosListagemSindico(Integer id, String nome, String email, String perfil) {
	
	public DadosListagemSindico(Sindico sindico) {
		this(sindico.getId(), sindico.getNome(), sindico.getEmail(), sindico.getPerfil());
	}
    
}
