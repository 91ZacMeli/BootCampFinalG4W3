package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.DiscountBlackFridayDTO;
import com.mercadolibre.demo.model.CuponsBlackFriday;
import com.mercadolibre.demo.model.DiscountBlackFriday;
import com.mercadolibre.demo.service.DiscountBlackFridayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/cupom-black-friday/")
public class DiscountBlackFridayController implements SecurityController {

    @Autowired
    DiscountBlackFridayService discountBlackFridayService;

    @PostMapping("/applydiscount")
    @ResponseBody
    public ResponseEntity<DiscountBlackFridayDTO> registerDiscount(CuponsBlackFriday cupomBlackFriday, Long idSeller) {
        try {
            DiscountBlackFridayDTO discountBlackFridayDTO = discountBlackFridayService.applyDiscountBlackFriday(cupomBlackFriday, idSeller);
            return new ResponseEntity<>(discountBlackFridayDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<DiscountBlackFridayDTO>> listDiscounts() {
        List<DiscountBlackFriday> discontsBlackFriday = discountBlackFridayService.listRegisterdsCoupons();
        return new ResponseEntity(discontsBlackFriday, HttpStatus.OK);
    }

    @GetMapping(value = "/listcupoms")
    @ResponseBody
    public ResponseEntity<List<DiscountBlackFridayDTO>> listEnums() {
        List<CuponsBlackFriday> discontsBlackFriday = discountBlackFridayService.listAllEnums();
        return new ResponseEntity(discontsBlackFriday, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DiscountBlackFriday> deleteCupom(@PathVariable Long id) {
        try {
            discountBlackFridayService.deleteDiscontBlackFriday(id);
            return new ResponseEntity("Cupom " + id + " deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
