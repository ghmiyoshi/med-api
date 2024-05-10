package br.com.alura.med.config;

import br.com.alura.med.infra.gateways.RevisionAuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "revisionAuditorAware")
public class PersistenceAuditConfig {

    @Bean
    public AuditorAware<String> revisionAuditorAware() {
        return new RevisionAuditorAwareImpl();
    }

}
