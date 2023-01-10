package br.com.alura.med.domain.service;

import br.com.alura.med.domain.model.Usuario;
import br.com.alura.med.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AutenticacaoService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

}
