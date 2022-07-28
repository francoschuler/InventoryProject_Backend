package com.company.InventoryBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.InventoryBackend.response.CategoryResponseREST;
import com.company.InventoryBackend.service.ICategoryService;

@RestController
@RequestMapping("/api/v1")
public class CategoryRESTController {
	
	@Autowired
	private ICategoryService service;
	
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseREST> searchCategories(){
		
		ResponseEntity<CategoryResponseREST> response = service.search();
		return response;
	}

}
