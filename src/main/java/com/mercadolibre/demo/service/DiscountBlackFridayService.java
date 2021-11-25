package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.DiscountBlackFridayDTO;
import com.mercadolibre.demo.dto.response.SalesAdDTOResponse;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.DiscountBlackFridayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mercadolibre.demo.model.CuponsBlackFriday.*;

@Service
public class DiscountBlackFridayService {

    private DiscountBlackFridayRepository discountBlackFridayRepoitory;

    @Autowired
    public DiscountBlackFridayService(DiscountBlackFridayRepository discountBlackFridayRepoitory) {
        this.discountBlackFridayRepoitory = discountBlackFridayRepoitory;
    }

    public DiscountBlackFridayDTO applyDiscountBlackFriday(CuponsBlackFriday cupomBlackFriday, Long idSeller) throws Exception {
        switch (cupomBlackFriday) {
            case FRIOSBLACK:
                List<SalesAdDTOResponse> listBySellerFrios = salesAdListBySeller(idSeller, "FRIOS");
                DiscountBlackFridayDTO discountBlackFridayFrios = new DiscountBlackFridayDTO(FRIOSBLACK, "15%", idSeller, listBySellerFrios);
                listBySellerFrios.stream().filter(s -> s.getCategoria().equals("FRIOS"))
                        .forEach(s -> s.setValueWithDescount(s.getValueItem() * 0.85));
                discountBlackFridayRepoitory.save(new DiscountBlackFriday(FRIOSBLACK.toString(), "15%", idSeller));
                return discountBlackFridayFrios;

            case CONGELADOSBLACK:
                List<SalesAdDTOResponse> listBySellerCongelados = salesAdListBySeller(idSeller, "CONGELADOS");
                DiscountBlackFridayDTO discountBlackFridayCongelados = new DiscountBlackFridayDTO(CONGELADOSBLACK, "20%", idSeller, listBySellerCongelados);
                listBySellerCongelados.stream().filter(s -> s.getCategoria().equals("CONGELADOS"))
                        .forEach(s -> s.setValueWithDescount(s.getValueItem() * 0.80));
                discountBlackFridayRepoitory.save(new DiscountBlackFriday(CONGELADOSBLACK.toString(), "20%", idSeller));
                return discountBlackFridayCongelados;

            case FRESCOSBLACK:
                List<SalesAdDTOResponse> listBySellerFrescos = salesAdListBySeller(idSeller, "AMBIENTE");
                DiscountBlackFridayDTO discountBlackFridayFrescos = new DiscountBlackFridayDTO(FRESCOSBLACK, "25%", idSeller, listBySellerFrescos);
                listBySellerFrescos.stream().filter(s -> s.getCategoria().equals("AMBIENTE"))
                        .forEach(s -> s.setValueWithDescount(s.getValueItem() * 0.75));
                discountBlackFridayRepoitory.save(new DiscountBlackFriday(FRESCOSBLACK.toString(), "25%", idSeller));
                return discountBlackFridayFrescos;

            default:
                throw new Exception("Selecione um cupom válido para cadastrar");
        }
    }

    public List<SalesAdDTOResponse> salesAdListBySeller(Long idSeller, String category) throws Exception {
        List<SalesAdDTOResponse> listBySller = discountBlackFridayRepoitory.salesAdBySeller(idSeller, category);
        if (!listBySller.isEmpty()) {
            return listBySller;
        } else {
            throw new Exception("Este vendedor não possui anuncios na categoria informada");
        }
    }

    public List<DiscountBlackFriday> listRegisterdsCoupons() {
        return discountBlackFridayRepoitory.findAll();
    }

    public List<CuponsBlackFriday> listAllEnums() {
        return Arrays.asList(CuponsBlackFriday.values());
    }
    public Optional<DiscountBlackFriday> findById(Long id) {
        return discountBlackFridayRepoitory.findById(id);
    }
    public String deleteDiscontBlackFriday(Long id) throws Exception {
        Optional<DiscountBlackFriday> existDiscontBlack = findById(id);
        if (existDiscontBlack.isPresent()) {
            discountBlackFridayRepoitory.deleteById(id);
            return "O cupom com o ID " + id + " Foi excluído com sucesso ";
        }else {
            throw new Exception("Falha ao deletar, Id DiscontBlack não cadastrado");
        }

    }

    public DiscountBlackFriday convertDiscountBlackFridayDTO(DiscountBlackFridayDTO dto) {
        return new DiscountBlackFriday(dto.getCuponsBlackFriday().toString(), dto.getCupomValue(), dto.getIdSeller());
    }

}
