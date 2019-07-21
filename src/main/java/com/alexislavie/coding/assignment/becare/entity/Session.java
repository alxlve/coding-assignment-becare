package com.alexislavie.coding.assignment.becare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Session {

    @Embedded
    private final Audit audit = new Audit();

    @Version
    @Column(name = "version", nullable = false)
    private long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            updatable = false
    )
    private User user;

    @NotNull
    @Past
    private LocalDateTime startDate;

    @NotNull
    @Past
    private LocalDateTime endDate;

    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(
            name = "measure",
            length = 64
    )
    @Column(
            name = "value",
            nullable = false,
            updatable = false
    )
    private Map<String, Double> measures;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private SessionState sessionState;
}
