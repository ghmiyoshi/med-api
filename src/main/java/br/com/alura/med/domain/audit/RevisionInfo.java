package br.com.alura.med.domain.audit;

import br.com.alura.med.infra.audit.RevisionAuditorAwareImpl;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Data
@Entity
@Table(name = "revision_info")
@RevisionEntity(RevisionAuditorAwareImpl.class)
public class RevisionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private int id;

    @RevisionTimestamp
    private long timestamp;

    private String user;

}
