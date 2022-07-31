package com.company.InventoryBackend.service;

import org.springframework.http.ResponseEntity;

import com.company.InventoryBackend.response.CategoryResponseREST;

public interface ICategoryService {
	public ResponseEntity<CategoryResponseREST> search();
	public ResponseEntity<CategoryResponseREST> searchById(Long id);
}
