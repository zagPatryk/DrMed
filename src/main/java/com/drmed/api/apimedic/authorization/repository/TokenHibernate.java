package com.drmed.api.apimedic.authorization.repository;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

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

    @Column(name = "API_MEDIC_TOKEN")
    private String apiMedicToken;

    @Column(name = "VALID_UNTIL")
    private LocalTime validUntil;

    public TokenHibernate(String apiMedicToken, LocalTime validUntil) {
        this.apiMedicToken = apiMedicToken;
        this.validUntil = validUntil;
    }
}
