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

    /** Associated account to notify*/
    @Column(name = "dna")
    private String dna;

    /** Payload Identifier */
    @Column(name = "type")
    @Enumerated(value= EnumType.STRING)
    private DNAType type;

    @PrePersist
    public void prePersist() {

        id  = UUID.randomUUID().toString();

    }

}
