package com.example.managingtransactions.listeners;

import com.example.managingtransactions.model.AuditRecord;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class AuditRecordListener {

    @PrePersist
    public void setRowAddedData(AuditRecord auditRecord) {
        auditRecord.setRowAddedDt(new Date());
        setRowUpdatedData(auditRecord);
    }

    @PreUpdate
    public void setRowUpdatedData(AuditRecord auditRecord) {
        auditRecord.setRowUpdatedDt(new Date());
    }
}
