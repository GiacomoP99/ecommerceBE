package com.aizoon.productproject.service;

import static com.aizoon.productproject.mapper.ProductMapper.mapDtoToEntity;
import static com.aizoon.productproject.mapper.ProductMapper.mapEntityToDto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aizoon.productproject.dto.ProductRequestDTO;
import com.aizoon.productproject.dto.ProductResponseDTO;
import com.aizoon.productproject.exceptions.ResourceNotFoundException;
import com.aizoon.productproject.model.Product;
import com.aizoon.productproject.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	@Autowired
	private CategoryService cserv;

	public List<ProductResponseDTO> getProducts() {
		// TODO Auto-generated method stub
		return repo.findAll().stream().map(prod -> mapEntityToDto(prod)).collect(Collectors.toList());
	}

	public List<ProductResponseDTO> getProductByCategory(Long cId) {
		// TODO Auto-generated method stub
		return repo.findAll().stream().filter(prod -> prod.getCategory().getId() == cId).map(ent -> mapEntityToDto(ent))
				.collect(Collectors.toList());
	}

	public ProductResponseDTO updateProduct(ProductRequestDTO prod, Long pId) throws ResourceNotFoundException {
		Product ent = mapDtoToEntity(prod);
		ent.setId(pId);
		ent.setCategory(cserv.getCategoryById(prod.getCategory()));
		repo.save(ent);
		ProductResponseDTO dto = mapEntityToDto(ent);
		return dto;

	}

	public ProductResponseDTO saveProduct(ProductRequestDTO prod) throws ResourceNotFoundException {
		Product ent = mapDtoToEntity(prod);
		ent.setCategory(cserv.getCategoryById(prod.getCategory()));
		repo.save(ent);
		ProductResponseDTO dto = mapEntityToDto(ent);
		return dto;
	}

	public ProductResponseDTO getProduct(Long id) throws ResourceNotFoundException {
		Product prod = null;
		prod = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		return mapEntityToDto(prod);
	}

	public void deleteProduct(Long id) {
		repo.deleteById(id);

	}

}
