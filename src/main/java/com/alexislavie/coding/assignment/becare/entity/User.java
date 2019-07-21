package com.alexislavie.coding.assignment.becare.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of = "email")
@Entity
public class User {

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
    @Email
    @Size(max = 254)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 64)
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 64)
    @Column(nullable = false)
    private String lastName;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Session> sessions;
}
