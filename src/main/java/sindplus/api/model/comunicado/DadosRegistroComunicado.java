package sindplus.api.model.comunicado;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record DadosRegistroComunicado(
		@NotBlank Integer condominioId,
		@NotBlank String tituloDoComunicado,
		@NotBlank String conteudoDoComunicado, 
		LocalDateTime data
) {

}
