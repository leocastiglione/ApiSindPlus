package sindplus.api.model.solicitacao;

import java.time.LocalDateTime;

public record DadosRegistroRespostaSolicitacao(
		String respostaSolicitacao, 
		Integer sindicoId, 
		String sindicoNome, 
		LocalDateTime dataResposta
		) {

}
