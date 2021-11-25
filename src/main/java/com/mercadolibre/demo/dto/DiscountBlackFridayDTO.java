package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.dto.response.SalesAdDTOResponse;
import com.mercadolibre.demo.model.CuponsBlackFriday;
import com.mercadolibre.demo.model.SalesAd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountBlackFridayDTO {

    private CuponsBlackFriday cuponsBlackFriday;
    private String cupomValue;
    private Long idSeller;
    private List<SalesAdDTOResponse> salesAdList;

    public DiscountBlackFridayDTO(CuponsBlackFriday cuponsBlackFriday, String cupomValue, Long idSeller) {
        this.cuponsBlackFriday = cuponsBlackFriday;
        this.cupomValue = cupomValue;
        this.idSeller = idSeller;
    }
}

