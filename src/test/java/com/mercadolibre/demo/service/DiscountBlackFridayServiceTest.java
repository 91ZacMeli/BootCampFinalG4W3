package com.mercadolibre.demo.service;

import static com.mercadolibre.demo.model.CuponsBlackFriday.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mercadolibre.demo.dto.DiscountBlackFridayDTO;
import com.mercadolibre.demo.dto.response.SalesAdDTOResponse;
import com.mercadolibre.demo.model.CuponsBlackFriday;
import com.mercadolibre.demo.model.DiscountBlackFriday;
import com.mercadolibre.demo.repository.DiscountBlackFridayRepository;
import com.mercadolibre.demo.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DiscountBlackFridayServiceTest {

    DiscountBlackFridayRepository mockDiscountBlackFridayRepository = Mockito.mock(DiscountBlackFridayRepository.class);
    DiscountBlackFridayService discountBlackFridayService = new DiscountBlackFridayService(mockDiscountBlackFridayRepository);

    @Test
    void applyDicountBlackFridayCaseFRIOSBLACK() throws Exception {

        List<SalesAdDTOResponse> listBySeller = new ArrayList<>();

        SalesAdDTOResponse salesAdDTOResponse = new SalesAdDTOResponse();
        salesAdDTOResponse.setCategoria("FRIOS");
        salesAdDTOResponse.setValueItem(15.0);
        salesAdDTOResponse.setProductName("Mortadela");
        salesAdDTOResponse.setSellerName("Roberto");

        SalesAdDTOResponse salesAdDTOResponse1 = new SalesAdDTOResponse();
        salesAdDTOResponse1.setCategoria("CONGELADOS");
        salesAdDTOResponse1.setValueItem(10.0);
        salesAdDTOResponse1.setProductName("Filé de Sardinha");
        salesAdDTOResponse1.setSellerName("RoGerio");

        SalesAdDTOResponse salesAdDTOResponse2 = new SalesAdDTOResponse();
        salesAdDTOResponse2.setCategoria("AMBIENTE");
        salesAdDTOResponse2.setValueItem(18.0);
        salesAdDTOResponse2.setProductName("Quiabo");
        salesAdDTOResponse2.setSellerName("Rodrigo");

        SalesAdDTOResponse salesAdDTOResponse3 = new SalesAdDTOResponse();
        salesAdDTOResponse3.setCategoria("CONGELADOS");
        salesAdDTOResponse3.setValueItem(13.0);
        salesAdDTOResponse3.setProductName("Filá de Frango");
        salesAdDTOResponse3.setSellerName("RoNy");

        listBySeller.add(salesAdDTOResponse);
        listBySeller.add(salesAdDTOResponse1);
        listBySeller.add(salesAdDTOResponse2);
        listBySeller.add(salesAdDTOResponse3);

        DiscountBlackFriday discountBlackFriday = new DiscountBlackFriday();
        discountBlackFriday.setIdDiscontBlackFriday(1L);
        discountBlackFriday.setCuponsBlackFriday("FRESCOSBLACK");
        discountBlackFriday.setCupomValue("15%");
        discountBlackFriday.setIdSeller(1L);

        Mockito.when(mockDiscountBlackFridayRepository.salesAdBySeller(1L, "FRIOS")).thenReturn(listBySeller);
        Mockito.when(mockDiscountBlackFridayRepository.save(Mockito.any(DiscountBlackFriday.class))).thenReturn(discountBlackFriday);

        discountBlackFridayService.applyDiscountBlackFriday(FRIOSBLACK, 1L);

        assertNotNull(listBySeller);
        assertNotNull(discountBlackFriday);

    }

    @Test
    void applyDicountBlackFridayCaseFRESCOSBLACK() throws Exception {

        List<SalesAdDTOResponse> listBySeller = new ArrayList<>();

        SalesAdDTOResponse salesAdDTOResponse = new SalesAdDTOResponse();
        salesAdDTOResponse.setCategoria("FRIOS");
        salesAdDTOResponse.setValueItem(15.0);
        salesAdDTOResponse.setProductName("Mortadela");
        salesAdDTOResponse.setSellerName("Roberto");

        SalesAdDTOResponse salesAdDTOResponse1 = new SalesAdDTOResponse();
        salesAdDTOResponse1.setCategoria("CONGELADOS");
        salesAdDTOResponse1.setValueItem(10.0);
        salesAdDTOResponse1.setProductName("Filé de Sardinha");
        salesAdDTOResponse1.setSellerName("RoGerio");

        SalesAdDTOResponse salesAdDTOResponse2 = new SalesAdDTOResponse();
        salesAdDTOResponse2.setCategoria("AMBIENTE");
        salesAdDTOResponse2.setValueItem(18.0);
        salesAdDTOResponse2.setProductName("Quiabo");
        salesAdDTOResponse2.setSellerName("Rodrigo");

        SalesAdDTOResponse salesAdDTOResponse3 = new SalesAdDTOResponse();
        salesAdDTOResponse3.setCategoria("CONGELADOS");
        salesAdDTOResponse3.setValueItem(13.0);
        salesAdDTOResponse3.setProductName("Filá de Frango");
        salesAdDTOResponse3.setSellerName("RoNy");

        listBySeller.add(salesAdDTOResponse);
        listBySeller.add(salesAdDTOResponse1);
        listBySeller.add(salesAdDTOResponse2);
        listBySeller.add(salesAdDTOResponse3);

        DiscountBlackFriday discountBlackFriday = new DiscountBlackFriday();
        discountBlackFriday.setIdDiscontBlackFriday(1L);
        discountBlackFriday.setCuponsBlackFriday("FRESCOSBLACK");
        discountBlackFriday.setCupomValue("15%");
        discountBlackFriday.setIdSeller(1L);

        Mockito.when(mockDiscountBlackFridayRepository.salesAdBySeller(1L, "AMBIENTE")).thenReturn(listBySeller);
        Mockito.when(mockDiscountBlackFridayRepository.save(Mockito.any(DiscountBlackFriday.class))).thenReturn(discountBlackFriday);

        discountBlackFridayService.applyDiscountBlackFriday(FRESCOSBLACK, 1L);

        assertNotNull(listBySeller);
        assertNotNull(discountBlackFriday);

    }

    @Test
    void applyDicountBlackFridayCaseCONGELADOSBLACK() throws Exception {

        List<SalesAdDTOResponse> listBySeller = new ArrayList<>();

        SalesAdDTOResponse salesAdDTOResponse = new SalesAdDTOResponse();
        salesAdDTOResponse.setCategoria("FRIOS");
        salesAdDTOResponse.setValueItem(15.0);
        salesAdDTOResponse.setProductName("Mortadela");
        salesAdDTOResponse.setSellerName("Roberto");

        SalesAdDTOResponse salesAdDTOResponse1 = new SalesAdDTOResponse();
        salesAdDTOResponse1.setCategoria("CONGELADOS");
        salesAdDTOResponse1.setValueItem(10.0);
        salesAdDTOResponse1.setProductName("Filé de Sardinha");
        salesAdDTOResponse1.setSellerName("RoGerio");

        SalesAdDTOResponse salesAdDTOResponse2 = new SalesAdDTOResponse();
        salesAdDTOResponse2.setCategoria("AMBIENTE");
        salesAdDTOResponse2.setValueItem(18.0);
        salesAdDTOResponse2.setProductName("Quiabo");
        salesAdDTOResponse2.setSellerName("Rodrigo");

        SalesAdDTOResponse salesAdDTOResponse3 = new SalesAdDTOResponse();
        salesAdDTOResponse3.setCategoria("CONGELADOS");
        salesAdDTOResponse3.setValueItem(13.0);
        salesAdDTOResponse3.setProductName("Filá de Frango");
        salesAdDTOResponse3.setSellerName("RoNy");

        listBySeller.add(salesAdDTOResponse);
        listBySeller.add(salesAdDTOResponse1);
        listBySeller.add(salesAdDTOResponse2);
        listBySeller.add(salesAdDTOResponse3);

        DiscountBlackFriday discountBlackFriday = new DiscountBlackFriday();
        discountBlackFriday.setIdDiscontBlackFriday(1L);
        discountBlackFriday.setCuponsBlackFriday("CONGELADOSBLACK");
        discountBlackFriday.setCupomValue("15%");
        discountBlackFriday.setIdSeller(1L);

        Mockito.when(mockDiscountBlackFridayRepository.salesAdBySeller(1L, "CONGELADOS")).thenReturn(listBySeller);
        Mockito.when(mockDiscountBlackFridayRepository.save(Mockito.any(DiscountBlackFriday.class))).thenReturn(discountBlackFriday);

        discountBlackFridayService.applyDiscountBlackFriday(CONGELADOSBLACK, 1L);


        assertNotNull(listBySeller);
        assertNotNull(discountBlackFriday);

    }
    @Test
    void applyDicountBlackFridayCaseDefault() throws Exception {

        List<SalesAdDTOResponse> listBySeller = new ArrayList<>();

        SalesAdDTOResponse salesAdDTOResponse = new SalesAdDTOResponse();
        salesAdDTOResponse.setCategoria("FRIOS");
        salesAdDTOResponse.setValueItem(15.0);
        salesAdDTOResponse.setProductName("Mortadela");
        salesAdDTOResponse.setSellerName("Roberto");

        SalesAdDTOResponse salesAdDTOResponse1 = new SalesAdDTOResponse();
        salesAdDTOResponse1.setCategoria("CONGELADOS");
        salesAdDTOResponse1.setValueItem(10.0);
        salesAdDTOResponse1.setProductName("Filé de Sardinha");
        salesAdDTOResponse1.setSellerName("RoGerio");

        SalesAdDTOResponse salesAdDTOResponse2 = new SalesAdDTOResponse();
        salesAdDTOResponse2.setCategoria("AMBIENTE");
        salesAdDTOResponse2.setValueItem(18.0);
        salesAdDTOResponse2.setProductName("Quiabo");
        salesAdDTOResponse2.setSellerName("Rodrigo");

        SalesAdDTOResponse salesAdDTOResponse3 = new SalesAdDTOResponse();
        salesAdDTOResponse3.setCategoria("CONGELADOS");
        salesAdDTOResponse3.setValueItem(13.0);
        salesAdDTOResponse3.setProductName("Filá de Frango");
        salesAdDTOResponse3.setSellerName("RoNy");

        listBySeller.add(salesAdDTOResponse);
        listBySeller.add(salesAdDTOResponse1);
        listBySeller.add(salesAdDTOResponse2);
        listBySeller.add(salesAdDTOResponse3);

        DiscountBlackFriday discountBlackFriday = new DiscountBlackFriday();
        discountBlackFriday.setIdDiscontBlackFriday(1L);
        discountBlackFriday.setCuponsBlackFriday("CONGELADOSBLACK");
        discountBlackFriday.setCupomValue("15%");
        discountBlackFriday.setIdSeller(1L);

        Mockito.when(mockDiscountBlackFridayRepository.salesAdBySeller(1L, "CONGELADOS")).thenReturn(listBySeller);
        Mockito.when(mockDiscountBlackFridayRepository.save(Mockito.any(DiscountBlackFriday.class))).thenReturn(discountBlackFriday);

        discountBlackFridayService.applyDiscountBlackFriday(CONGELADOSBLACK, 1L);
        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            discountBlackFridayService.applyDiscountBlackFriday(DEFINIRCUPOM,3L);
        });

        assertEquals(exceptionThatWasThrown.getMessage(), "Selecione um cupom válido para cadastrar");
    }

    @Test
    void salesADListBySellerTest() throws Exception {
        List<SalesAdDTOResponse> listBySller = new ArrayList<>();
        SalesAdDTOResponse salesAdDTOResponse = new SalesAdDTOResponse();
        SalesAdDTOResponse salesAdDTOResponse1 = new SalesAdDTOResponse();
        SalesAdDTOResponse salesAdDTOResponse2 = new SalesAdDTOResponse();
        SalesAdDTOResponse salesAdDTOResponse3 = new SalesAdDTOResponse();

        listBySller.add(salesAdDTOResponse);
        listBySller.add(salesAdDTOResponse1);
        listBySller.add(salesAdDTOResponse2);
        listBySller.add(salesAdDTOResponse3);

        Mockito.when(mockDiscountBlackFridayRepository.salesAdBySeller
                (Mockito.any(Long.class), Mockito.any(String.class))).thenReturn(listBySller);

        List<SalesAdDTOResponse> salesAdDTOResponseList = discountBlackFridayService.salesAdListBySeller(1L, "FRIOS");

        assertNotNull(salesAdDTOResponseList);

    }
    @Test
    void salesADListBySellerEmptyTest() throws Exception {
        List<SalesAdDTOResponse> listBySller = new ArrayList<>();

        Mockito.when(mockDiscountBlackFridayRepository.salesAdBySeller
                (Mockito.any(Long.class), Mockito.any(String.class))).thenReturn(listBySller);

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            discountBlackFridayService.salesAdListBySeller(1L,"FRIOS");
        });

        assertEquals(exceptionThatWasThrown.getMessage(), "Este vendedor não possui anuncios na categoria informada");

    }

    @Test
    void listRegisterdsCouponsTest() {

        List<DiscountBlackFriday> listRegisterdsCoupons = new ArrayList<>();
        DiscountBlackFriday discountBlackFriday = new DiscountBlackFriday();
        DiscountBlackFriday discountBlackFriday1 = new DiscountBlackFriday();
        DiscountBlackFriday discountBlackFriday2 = new DiscountBlackFriday();

        listRegisterdsCoupons.add(discountBlackFriday);
        listRegisterdsCoupons.add(discountBlackFriday1);
        listRegisterdsCoupons.add(discountBlackFriday2);

        Mockito.when(mockDiscountBlackFridayRepository.findAll()).thenReturn(listRegisterdsCoupons);

        List<DiscountBlackFriday> getlistRegisterdsCouponns = discountBlackFridayService.listRegisterdsCoupons();

        assertNotNull(getlistRegisterdsCouponns);

    }

    @Test
    void listEnumsTest() {

        List<CuponsBlackFriday> listAllEnums = Arrays.asList(CuponsBlackFriday.values());

        assertNotNull(listAllEnums);
        assertEquals(listAllEnums.get(0), CuponsBlackFriday.DEFINIRCUPOM);
        assertEquals(listAllEnums.get(1), FRIOSBLACK);
    }

    @Test
    void findByIDTest() {

        DiscountBlackFriday discountBlackFriday = new DiscountBlackFriday();
        discountBlackFriday.setIdDiscontBlackFriday(1L);
        Mockito.when(mockDiscountBlackFridayRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(discountBlackFriday));

        Optional<DiscountBlackFriday> discountBlackFridayObtido = discountBlackFridayService.findById(1L);

        assertNotNull(discountBlackFriday);
        assertEquals(1L, discountBlackFridayObtido.get().getIdDiscontBlackFriday());

    }


    @Test
    void deleteDiscontBlackFridayTestNoSuccess() throws Exception {
        DiscountBlackFriday discountBlackFriday = new DiscountBlackFriday();
        discountBlackFriday.setIdDiscontBlackFriday(1L);

        Mockito.when(mockDiscountBlackFridayRepository.findById(1L)).thenReturn(Optional.of(discountBlackFriday));

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            discountBlackFridayService.deleteDiscontBlackFriday( 39L);
        });

        assertEquals(exceptionThatWasThrown.getMessage(), "Falha ao deletar, Id DiscontBlack não cadastrado");
    }

    @Test
    void deleteDiscontBlackFridayTest() throws Exception {
        DiscountBlackFriday discountBlackFriday = new DiscountBlackFriday();
        discountBlackFriday.setIdDiscontBlackFriday(1L);

        Mockito.when(mockDiscountBlackFridayRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(discountBlackFriday));

        discountBlackFridayService.deleteDiscontBlackFriday(1L);
        Mockito.verify(mockDiscountBlackFridayRepository).deleteById(1L);
    }
}
