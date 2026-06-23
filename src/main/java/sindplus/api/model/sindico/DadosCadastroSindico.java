package sindplus.api.model.sindico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroSindico(
		
		@NotBlank String nome,
		
		@Email @NotBlank String email,
		
		@NotBlank String senha
) {

}
