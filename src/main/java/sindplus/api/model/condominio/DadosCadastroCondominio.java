package sindplus.api.model.condominio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCondominio(
		@NotBlank @NotNull String cnpj,
		@NotBlank String nome,
		@NotBlank @NotNull String endereco,
		String email,
		@NotNull Integer sindicoId
		) {

}
