package sindplus.api.model.sindico;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "sindicos")

public class Sindico {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Boolean ativo = true;

    public Sindico(DadosCadastroSindico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }

    public void alterarDados(DadosAtualizacaoSindico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.email() != null) {
            this.email = dados.email();
        }

    }

    public void exclusaoLogica() {
        this.ativo = false;
    }
}