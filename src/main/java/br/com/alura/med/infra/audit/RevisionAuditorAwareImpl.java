package br.com.alura.med.infra.audit;

import br.com.alura.med.domain.audit.RevisionInfo;
import br.com.alura.med.domain.usuario.Usuario;
import org.hibernate.envers.RevisionListener;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class RevisionAuditorAwareImpl implements AuditorAware<String>, RevisionListener {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(Usuario.class::cast)
                .map(Usuario::getUsername);
    }

    @Override
    public void newRevision(Object revisionEntity) {
        RevisionInfo revisionInfo = (RevisionInfo) revisionEntity;
        getCurrentAuditor().ifPresent(revisionInfo::setUser);
    }

}
