package com.company.InventoryBackend.service;

import org.springframework.http.ResponseEntity;

import com.company.InventoryBackend.model.Category;
import com.company.InventoryBackend.response.CategoryResponseREST;

public interface ICategoryService {
	public ResponseEntity<CategoryResponseREST> search();
	public ResponseEntity<CategoryResponseREST> searchById(Long id);
	public ResponseEntity<CategoryResponseREST> save(Category category);
	public ResponseEntity<CategoryResponseREST> update(Category category, Long id);
	public ResponseEntity<CategoryResponseREST> deleteById(Long id);
}
