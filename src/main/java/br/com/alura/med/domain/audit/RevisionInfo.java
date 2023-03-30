package br.com.alura.med.domain.audit;

import br.com.alura.med.infra.audit.RevisionAuditorAwareImpl;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serializable;

@Data
@Entity
@Table(name = "revision_info")
@RevisionEntity(RevisionAuditorAwareImpl.class)
public class RevisionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private int id;

    @RevisionTimestamp
    private long timestamp;

    private String user;

}
