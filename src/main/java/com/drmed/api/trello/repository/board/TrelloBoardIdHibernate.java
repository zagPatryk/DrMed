package com.drmed.api.trello.repository.board;

import com.drmed.base.doctor.repository.DoctorHibernate;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TRELLO_BOARD")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TrelloBoardIdHibernate {
    @Id
    @Column(name = "TRELLO_BOARD_INTERNAL_ID")
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "TRELL_BOARD_EXTERNAL_ID")
    private String trelloBoardId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "DOCTOR_ID")
    private DoctorHibernate doctor;

    public TrelloBoardIdHibernate(String trelloBoardId, DoctorHibernate doctor) {
        this.trelloBoardId = trelloBoardId;
        this.doctor = doctor;
    }
}
