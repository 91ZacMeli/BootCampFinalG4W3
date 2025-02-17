package com.mercadolibre.demo.service;

import java.util.List;
import java.util.Optional;

import com.mercadolibre.demo.dto.SalesAdDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.ProductRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;
import com.mercadolibre.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class SalesAdService {

    private SalesAdRepository salesAdRepository;
    private SellerRepository sellerRepository;
    private ProductRepository productRepository;

    @Autowired
    public SalesAdService(SalesAdRepository salesAdRepository,
                          SellerRepository sellerRepository,
                          ProductRepository productRepository) {
        this.salesAdRepository = salesAdRepository;
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    public SalesAd save(SalesAdDTO dto) throws Exception {
        SalesAd salesAd = convertSalesAdDTO(dto);
        return salesAdRepository.save(salesAd);
    }

    public List<SalesAd> list() {
        return salesAdRepository.findAll();
    }

    public Optional<SalesAd> findById(Long id) {
        return salesAdRepository.findById(id);
    }

    public Optional<Seller> getSeller(SalesAdDTO dto) {
        Optional<Seller> seller = sellerRepository.findById(dto.getIdSeller());
            return seller;
    }
    
    public Optional<Product> getProduct(SalesAdDTO dto){
        Optional<Product> product = productRepository.findById(dto.getIdProduct());
        return product;
    }
    public SalesAd update(SalesAdDTO dto, Long id) throws Exception {
        Optional<SalesAd> existSalesAd = findById(id);
        if (existSalesAd.isPresent()) {
            SalesAd salesAd = convertSalesAdDTO(dto);
            salesAd.setId(id);
            return salesAdRepository.saveAndFlush(salesAd);
        } else {
            throw new Exception("Id não cadastrado");
        }
    }

    public SalesAd convertSalesAdDTO(SalesAdDTO dto) throws Exception {
        if (getProduct(dto).isPresent() && getSeller(dto).isPresent()) {
            SalesAd  salesAd = new SalesAd(dto.getVolume(), dto.getMinimumTemperature(),
                    dto.getMaximumTemperature(), dto.getPrice(), getSeller(dto), getProduct(dto));
            return salesAd;
        } else {
        	throw new Exception("Id do produto não cadastrado");
        }
    }
}
