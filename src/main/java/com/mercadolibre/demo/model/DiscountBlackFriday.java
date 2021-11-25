package com.mercadolibre.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "discontBlackFriday")
public class DiscountBlackFriday {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDiscontBlackFriday")
    private Long idDiscontBlackFriday;

    @Column(name = "cupomName", nullable = false)
    private String cuponsBlackFriday;

    @Column(name = "cupomValue", nullable = false)
    private String cupomValue;

    @Column(name = "idSeller", nullable = false)
    private Long idSeller;

    public DiscountBlackFriday(String cuponsBlackFriday, String cupomValue, Long idSeller) {
        this.cuponsBlackFriday = cuponsBlackFriday;
        this.cupomValue = cupomValue;
        this.idSeller = idSeller;
    }
}
