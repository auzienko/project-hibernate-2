package com.javarush.hibernate2.listener;

import com.javarush.hibernate2.entity.BaseEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LastUpdateListener {

    @PrePersist
    @PreUpdate
    public void setLastUpdate(Object entity) {
        setLastUpdateTimestamp(entity);
    }

    private void setLastUpdateTimestamp(Object entity) {
        if (entity instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entity;
            baseEntity.setLastUpdate(now());
        }
    }

    private Timestamp now() {
        return Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
    }

}
