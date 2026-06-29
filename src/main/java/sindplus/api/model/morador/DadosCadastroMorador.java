package sindplus.api.model.morador;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroMorador(
		@NotBlank String nome, 
		@NotBlank @Email String email, 
		String senha, 
		String unidade, 
		Integer condominioId
) {

}
