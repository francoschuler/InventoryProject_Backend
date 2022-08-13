package com.company.InventoryBackend.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.InventoryBackend.model.Product;
import com.company.InventoryBackend.response.ProductResponseREST;
import com.company.InventoryBackend.service.IProductService;
import com.company.InventoryBackend.util.Util;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRESTController {
	
	private IProductService productService;
	
	public ProductRESTController(IProductService productService) {
		super();
		this.productService = productService;
	}

	/**
	 * Saves a new product
	 * @param picture
	 * @param name
	 * @param price
	 * @param amount
	 * @param categoryId
	 * @return ResponseEntity<ProductResponseREST>
	 * @throws IOException
	 */
	@PostMapping("/products")
	public ResponseEntity<ProductResponseREST> save(
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("amount") int amount,
			@RequestParam("categoryId") Long categoryId
			) throws IOException
	{
		
		Product product = new Product();		
		product.setName(name);
		product.setPrice(price);
		product.setAmount(amount);
		product.setPicture(Util.compressZLib(picture.getBytes()));
		
		ResponseEntity<ProductResponseREST> response = productService.save(product, categoryId);
		return response;
		
	}
	
	/**
	 * Searchs product by ID
	 * @param id
	 * @return
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseREST> searchById(@PathVariable Long id) {
		ResponseEntity<ProductResponseREST> response = productService.searchById(id);
		return response;
	}

	
}
