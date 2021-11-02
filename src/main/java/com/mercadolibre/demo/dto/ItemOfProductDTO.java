package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import com.mercadolibre.demo.model.SalesAd;

@Getter
@NoArgsConstructor
public class ItemOfProductDTO {
        private Long quantity;
        private Long salesAd;
}