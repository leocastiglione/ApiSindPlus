package sindplus.api.model.comunicado;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name="comunicados")

public class Comunicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "condominioId")
    private Condominio condominio;
    private String tituloDoComunicado;
    private String conteudoDoComunicado;
    private LocalDateTime data;

    public Comunicado(DadosRegistroComunicado dados) {
    	this.tituloDoComunicado = dados.tituloDoComunicado();
        this.conteudoDoComunicado = dados.conteudoDoComunicado();
    }


}
