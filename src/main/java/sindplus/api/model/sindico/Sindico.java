package sindplus.api.model.sindico;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


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

public class Sindico implements UserDetails{
    
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
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // or roles later
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
        return this.ativo;
    }
    
}