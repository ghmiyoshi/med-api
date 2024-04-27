package br.com.alura.med.naousar.domain.repository;

import br.com.alura.med.naousar.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @EntityGraph(attributePaths = {"perfis"})
    Optional<Usuario> findByLogin(String login);

}
