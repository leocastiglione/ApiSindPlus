package sindplus.api.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sindplus.api.model.morador.MoradorRepository;
import sindplus.api.model.sindico.SindicoRepository;


@Service
public class UsuarioDetailsService implements UserDetailsService {
	
	@Autowired
	private SindicoRepository sindicoRepository;
	@Autowired
	private MoradorRepository moradorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("LOGIN EMAIL: " + email);
		System.out.println("FOUND SINDICO: " + sindicoRepository.findByEmail(email).isPresent());
		System.out.println("FOUND MORADOR: " + moradorRepository.findByEmail(email).isPresent());
		

        return sindicoRepository.findByEmail(email)
                .map(UserDetails.class::cast)
                .or(() -> moradorRepository.findByEmail(email)
                        .map(UserDetails.class::cast))
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado"));
		
	}

}
 