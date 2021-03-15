package com.mercadolibre.mutantsxmen.dataProvider.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;

/**
 * The DNA Model Abstraction used to Registry after validation
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = false)
@Table(name = "dna", schema = "brotherhood_of_mutants")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class DNAModel extends Auditable<String> implements Serializable {

    /** The serial version class */
    private static final long serialVersionUID = 1L;

    /** Entity ID - UUID */
    @Id
    @Column(name = "id")
    private String id;

    /** The DNA Sequence analyzed */
    @Column(name = "dna")
    private String dna;

    /** The Type of the DNA (Mutant or Human) */
    @Column(name = "dna_type")
    @Enumerated(value= EnumType.STRING)
    private DNAType dnaType;

    @PrePersist
    public void prePersist() {

        id  = UUID.randomUUID().toString();

    }

}
