package com.mercadolibre.mutantsxmen.dataProvider.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Capture auditing information on persisting and updating models
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Data
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U>{

    /** User who created the record */
    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private U createdBy;

    /** Date the record was created */
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    /** User who updated the record */
    @LastModifiedBy
    @Column(name = "updated_by")
    private U updatedBy;

    /** Date the record was updated */
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

}
