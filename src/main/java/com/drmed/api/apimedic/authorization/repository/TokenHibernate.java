package com.drmed.api.apimedic.authorization.repository;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "API_MEDIC_TOKEN")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TokenHibernate {
    @Id
    @Column(name = "TOKEN_ID")
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "API_MEDIC_TOKEN", length = 860)
    private String apiMedicToken;

    @Column(name = "VALID_UNTIL")
    private LocalDateTime validUntil;

    public TokenHibernate(String apiMedicToken, LocalDateTime validUntil) {
        this.apiMedicToken = apiMedicToken;
        this.validUntil = validUntil;
    }
}
