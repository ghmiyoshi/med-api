package br.com.alura.med.application.usecases.autenticacao;

import br.com.alura.med.infra.persistence.usuario.UsuarioEntity;
import br.com.alura.med.infra.persistence.usuario.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AutenticarUsuario implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity loadUserByUsername(final String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado"));
    }

}
