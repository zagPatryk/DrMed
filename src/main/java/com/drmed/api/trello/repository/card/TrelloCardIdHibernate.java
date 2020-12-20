package com.drmed.api.trello.repository.card;

import com.drmed.base.order.repository.OrderHibernate;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TRELLO_CARD")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TrelloCardIdHibernate {
    @Id
    @Column(name = "TRELLO_CARD_INTERNAL_ID")
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "TRELL_CARD_EXTERNAL_ID")
    private String trelloBoardId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ORDER_ID")
    private OrderHibernate order;

    public TrelloCardIdHibernate(String trelloBoardId, OrderHibernate order) {
        this.trelloBoardId = trelloBoardId;
        this.order = order;
    }
}
