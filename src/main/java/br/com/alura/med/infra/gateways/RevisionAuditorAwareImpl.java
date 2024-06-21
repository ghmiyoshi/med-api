package br.com.alura.med.infra.gateways;

import br.com.alura.med.infra.persistence.RevisionInfo;
import br.com.alura.med.infra.persistence.usuario.UsuarioEntity;
import java.util.Optional;
import org.hibernate.envers.RevisionListener;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class RevisionAuditorAwareImpl implements AuditorAware<String>, RevisionListener {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(UsuarioEntity.class::cast)
                .map(UsuarioEntity::getUsername);
    }

    @Override
    public void newRevision(Object revisionEntity) {
        RevisionInfo revisionInfo = (RevisionInfo) revisionEntity;
        getCurrentAuditor().ifPresent(revisionInfo::setUser);
    }
}
