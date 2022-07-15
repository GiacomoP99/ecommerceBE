package com.aizoon.productproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aizoon.productproject.dto.ProductRequestDTO;
import com.aizoon.productproject.dto.ProductResponseDTO;
import com.aizoon.productproject.exceptions.ResourceNotFoundException;
import com.aizoon.productproject.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ProductController extends BaseController {

	@Autowired
	private ProductService prodServ;

	@GetMapping("/public/products")
	public List<ProductResponseDTO> getAllProducts() {
		return prodServ.getProducts();
	}

	@GetMapping("/public/products/{id}")
	public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable(value = "id") Long pId)
			throws ResourceNotFoundException {
		ProductResponseDTO product = prodServ.getProduct(pId);

		return ResponseEntity.ok().body(product);
	}

	@GetMapping("/public/products/category/{idC}")
	public ResponseEntity<List<ProductResponseDTO>> getProductByCategory(@PathVariable(value = "idC") Long cId)
			throws ResourceNotFoundException {
		List<ProductResponseDTO> products = prodServ.getProductByCategory(cId);

		return ResponseEntity.ok().body(products);
	}

	// http://localhost:8080/ProgettoImpiegatiApplication/api/v1/Products
	@PostMapping("/private/products")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequestDTO product)
			throws ResourceNotFoundException {

		ProductResponseDTO emp = null;
		emp = prodServ.saveProduct(product);
		return ResponseEntity.ok(emp);

	}
//			catch (Exception e2) {
//				// TODO: handle exception
//			}

	@PutMapping("/private/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable(value = "id") Long pId,
			@Valid @RequestBody ProductRequestDTO prod) throws ResourceNotFoundException {

		final ProductResponseDTO updatedProduct = prodServ.updateProduct(prod, pId);
		return ResponseEntity.ok(updatedProduct);

	}

	@DeleteMapping("/private/products/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long pId) throws ResourceNotFoundException {
		// Product Product = ProductService.getProduct(ProductId);
		prodServ.deleteProduct(pId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
