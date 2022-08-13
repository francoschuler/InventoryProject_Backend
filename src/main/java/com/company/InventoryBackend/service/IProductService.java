package com.company.InventoryBackend.service;

import org.springframework.http.ResponseEntity;

import com.company.InventoryBackend.model.Product;
import com.company.InventoryBackend.response.ProductResponseREST;

public interface IProductService {
	
	public ResponseEntity<ProductResponseREST> save(Product product, Long categoryId);
	public ResponseEntity<ProductResponseREST> searchById(Long id);
	public ResponseEntity<ProductResponseREST> searchByName(String name);
	public ResponseEntity<ProductResponseREST> deleteById(Long id);
	public ResponseEntity<ProductResponseREST> search();
}
