package sindplus.api.model.condominio;

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
import sindplus.api.model.sindico.Sindico;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name="condominios")

public class Condominio {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cnpj;
    private String nome;
    private String endereco;
    private String email;
    private Boolean ativo = true;
    
    @ManyToOne
    @JoinColumn(name = "sindicoId")
    private Sindico sindico;
    

    public Condominio(DadosCadastroCondominio dados) {
        this.cnpj = dados.cnpj();
        this.nome = dados.nome();
        this.endereco = dados.endereco();
        this.email = dados.email();
        this.ativo = true;
    }

    public void alterarDados(DadosAtualizacaoCondominio dados) {
        if (dados.cnpj() != null) {
            this.cnpj = dados.cnpj();
        }

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.endereco() != null) {
            this.endereco = dados.endereco();
        }

        if (dados.email() != null) {
            this.email = dados.email();
        }

    }

    public void exclusaoLogica() {
        this.ativo = false;
    }

}
