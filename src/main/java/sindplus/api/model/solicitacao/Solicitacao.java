package sindplus.api.model.solicitacao;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sindplus.api.model.condominio.Condominio;
import sindplus.api.model.morador.Morador;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name="solicitacoes")

public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "condominioId")
    private Condominio condominio;
    @ManyToOne
    @JoinColumn(name = "moradorId")
    private Morador morador;
    private String descricaoSolicitacao;
    private String respostaSolicitacao;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataResposta;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Solicitacao(DadosRegistroSolicitacao dados) {
        this.descricaoSolicitacao = dados.descricaoSolicitacao();
    }


}
