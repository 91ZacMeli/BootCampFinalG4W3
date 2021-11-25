package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ProductDTO;
import com.mercadolibre.demo.dto.response.DueDateDTO;
import com.mercadolibre.demo.model.InboundOrder;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.repository.InboundOrderRepository;
import com.mercadolibre.demo.repository.ProductRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

	private ProductRepository productRepository;
	private InboundOrderRepository inboundOrderRepository;

	@Autowired
	public ProductService(ProductRepository productRepository, InboundOrderRepository inboundOrderRepository) {
		this.productRepository = productRepository;
		this.inboundOrderRepository = inboundOrderRepository;
	}

	public Product save(ProductDTO dto) {
		Product product = convertProductToDTO(dto);
		return productRepository.save(product);
	}

	public List<Product> list() {
		return productRepository.findAll();
	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	public Product update(ProductDTO dto, Long id) throws Exception {
		Optional<Product> existProduct = findById(id);
		if(existProduct.isPresent()) {
			Product product = convertProductToDTO(dto);
			product.setId(id);
			return productRepository.saveAndFlush(product);
		}  else {
			throw new Exception("Id não cadastrado");
		}
	}

	public void delete(Long id) throws Exception {
		Optional<Product> existProduct = findById(id);
		if(existProduct.isPresent()) {
			productRepository.deleteById(id);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public Product convertProductToDTO(ProductDTO dto) {
		return new Product(dto.getName(), dto.getDescription());
	}

	public List<DueDateDTO> dueDate(Long numberOfDay, Long idSection){
		List<InboundOrder> dueDateList = inboundOrderRepository.findAll();
		List<DueDateDTO> dueDateDTOList = new ArrayList<>();
		final LocalDate dataAtual = LocalDate.now();
		LocalDate daysExpire = dataAtual.minusDays(numberOfDay);


		for (InboundOrder itemInboundOrder : dueDateList){
			DueDateDTO requisiteFive = new DueDateDTO();
			if(itemInboundOrder.getSection().getIdSection().equals(idSection)){
			requisiteFive.setBatchNumber(itemInboundOrder.getBatchStock().getIdBatchNumber());
			requisiteFive.setProductId(itemInboundOrder.getBatchStock().getIdSalesAd().getProduct().getName());
			requisiteFive.setProductTypeID(itemInboundOrder.getSection().getCategory());
			requisiteFive.setDueDate(itemInboundOrder.getBatchStock().getDueDate());
			requisiteFive.setQuantity(itemInboundOrder.getBatchStock().getCurrentQuantity());
			dueDateDTOList.add(requisiteFive);
			}
		}
		List<DueDateDTO> result = dueDateDTOList.stream().filter(d -> d.getDueDate().isAfter(daysExpire)).collect(Collectors.toList());

		Collections.sort(result, Comparator.comparing(DueDateDTO::getDueDate));

		return result;
	}

	public List<DueDateDTO> dueDateCustom(Long numberOfDay,String CategoryName,String typeOfList) throws Exception {
		List<InboundOrder> dueDateList = inboundOrderRepository.findAll();
		List<DueDateDTO> dueDateDTOList = new ArrayList<>();

		final LocalDate dataAtual = LocalDate.now();
		LocalDate daysExpire = dataAtual.minusDays(numberOfDay);

		for (InboundOrder itemInboundOrder : dueDateList){
			DueDateDTO requisiteFive = new DueDateDTO();
			requisiteFive.setBatchNumber(itemInboundOrder.getBatchStock().getIdBatchNumber());
			requisiteFive.setProductId(itemInboundOrder.getBatchStock().getIdSalesAd().getProduct().getName());
			requisiteFive.setProductTypeID(itemInboundOrder.getSection().getCategory());
			requisiteFive.setDueDate(itemInboundOrder.getBatchStock().getDueDate());
			requisiteFive.setQuantity(itemInboundOrder.getBatchStock().getCurrentQuantity());
			dueDateDTOList.add(requisiteFive);
		}

		List<DueDateDTO> filter = dueDateDTOList.stream().filter(d -> d.getDueDate().isAfter(daysExpire)).collect(Collectors.toList());

		if (typeOfList.equals("asc")){
			Collections.sort(filter, Comparator.comparing(DueDateDTO::getDueDate));
		}else if (typeOfList.equals("desc")){
			Collections.sort(filter, (o1, o2) -> o2.getDueDate().compareTo(o1.getDueDate()));
		}
		else {
			throw new Exception("Escreva asc para ascendente ou desc para descendente");
		}
		return filter;
	}
}
