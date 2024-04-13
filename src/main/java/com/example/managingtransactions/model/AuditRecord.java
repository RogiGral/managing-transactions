package com.example.managingtransactions.model;

import com.example.managingtransactions.listeners.AuditRecordListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@EntityListeners(AuditRecordListener.class)
@MappedSuperclass
@Getter
@Setter
public class AuditRecord {

    @Basic(optional = false)
    @Column(name = "ROW_ADDED_DT", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowAddedDt;

    @Column(name = "ROW_UPDATED_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowUpdatedDt;
}
