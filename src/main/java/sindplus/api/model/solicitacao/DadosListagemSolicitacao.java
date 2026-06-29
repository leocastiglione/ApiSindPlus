package sindplus.api.model.solicitacao;

import java.time.LocalDateTime;

public record DadosListagemSolicitacao(
    String descricaoSolicitacao,
    LocalDateTime dataSolicitacao,
    Integer condominioId,
    Integer sindicoId,
    String sindicoNome,
    Integer moradorId,
    String moradorUnidade,
    Status status,
    Categoria categoria,
    String respostaSolicitacao
) {

    public DadosListagemSolicitacao(Solicitacao solicitacao) {
        this(solicitacao.getDescricaoSolicitacao(), 
        	solicitacao.getDataSolicitacao(),
        	solicitacao.getCondominio().getId(),
            solicitacao.getCondominio().getSindico().getId(),
            solicitacao.getCondominio().getSindico().getNome(),
            solicitacao.getMorador().getId(),
            solicitacao.getMorador().getUnidade(),
            solicitacao.getStatus(),
            solicitacao.getCategoria(),
            solicitacao.getRespostaSolicitacao()
        );
    }
}
