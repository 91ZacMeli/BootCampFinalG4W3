package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.response.SalesAdDTOResponse;
import com.mercadolibre.demo.model.DiscountBlackFriday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiscountBlackFridayRepository extends JpaRepository<DiscountBlackFriday, Long> {

    @Query(value = "SELECT iop.salesAd.price FROM ItemOfProduct iop WHERE iop.purchaseOrder.id =?1")
    Double price(Long name);

    @Query(value = "SELECT SUM(iop.quantity) FROM ItemOfProduct iop WHERE iop.purchaseOrder.id =?1")
    Long quantity(Long name);

    @Query(value = " select new com.mercadolibre.demo.dto.response.SalesAdDTOResponse(s.seller.name,s.product.name,s.price,sec.category)" +
            " from Section sec, InboundOrder i, BatchStock bs, SalesAd s,  Product p, Seller sl" +
            " where s.product.id = p.id " +
            " and bs.idSalesAd.id = s.id " +
            " and i.batchStock.idBatchNumber = bs.idBatchNumber " +
            " and i.section.idSection = sec.idSection " +
            " and s.seller.idseller = sl.idseller " +
            " and s.seller.idseller = ?1 " +
            " and sec.category = ?2 ")
    List<SalesAdDTOResponse> salesAdBySeller(Long idSeller,String category);
}
