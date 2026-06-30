package sindplus.api.model.morador;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
@Table(name="moradores")

public class Morador implements UserDetails {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String unidade;
    @ManyToOne
    @JoinColumn(name = "condominioId")
    private Condominio condominio;
    private Boolean ativo = true;
    private String perfil;

    public Morador(DadosCadastroMorador dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.unidade = dados.unidade();
    }

    public void alterarDados(DadosAtualizacaoMorador dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.email() != null) {
            this.email = dados.email();
        }

        if (dados.unidade() != null) {
            this.unidade = dados.unidade();
        }

    }

    public void exclusaoLogica() {
        this.ativo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(this.ativo);
    }

}
