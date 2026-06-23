package sindplus.api.model.solicitacao;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record DadosRegistroSolicitacao(
		@NotBlank 
		String descricaoSolicitacao, 
		LocalDateTime dataSolicitacao, 
		Integer condominioId, 
		Integer moradorId, 
		Status status) {

}
