package br.com.alura.med.infra.persistence.usuario;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    @EntityGraph(attributePaths = {"perfis"})
    Optional<UsuarioEntity> findByLogin(String login);

}
