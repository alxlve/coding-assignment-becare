package com.alexislavie.coding.assignment.becare.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor
@Data
@Embeddable
public class Audit {

    @NotNull
    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @NotNull
    @Column(name = "updated_on", nullable = false)
    private LocalDateTime updatedOn;

    @PrePersist
    public void prePersist() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC);

        createdOn = localDateTime;
        updatedOn = localDateTime;
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now(ZoneOffset.UTC);
    }
}
