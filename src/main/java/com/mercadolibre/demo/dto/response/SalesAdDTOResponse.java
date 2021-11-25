package com.mercadolibre.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesAdDTOResponse {

    private String sellerName;
    private String productName;
    private Double valueItem;
    private String categoria;
    private Double valueWithDescount;

    public SalesAdDTOResponse(String sellerName, String productName, Double valueItem, String categoria) {
        this.sellerName = sellerName;
        this.productName = productName;
        this.valueItem = valueItem;
        this.categoria = categoria;
    }
}
