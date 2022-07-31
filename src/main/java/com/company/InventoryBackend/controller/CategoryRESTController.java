package com.company.InventoryBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.InventoryBackend.model.Category;
import com.company.InventoryBackend.response.CategoryResponseREST;
import com.company.InventoryBackend.service.ICategoryService;

@RestController
@RequestMapping("/api/v1")
public class CategoryRESTController {
	
	@Autowired
	private ICategoryService service;
	
	/**
	 * Gets all the categories.
	 * @return
	 */
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseREST> searchCategories(){
		
		ResponseEntity<CategoryResponseREST> response = service.search();
		return response;
	}
	
	/**
	 * Gets categories by id.
	 * @param id
	 * @return
	 */
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseREST> searchCategoriesById(@PathVariable Long id){
		
		ResponseEntity<CategoryResponseREST> response = service.searchById(id);
		return response;
	}
	
	/**
	 * Saves categories.
	 * @param category
	 * @return
	 */
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseREST> save(@RequestBody Category category){
		
		ResponseEntity<CategoryResponseREST> response = service.save(category);
		return response;
	}
	
	/**
	 * Updates categories by id.
	 * @param category
	 * @param id
	 * @return
	 */
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseREST> save(@RequestBody Category category, @PathVariable Long id){
		
		ResponseEntity<CategoryResponseREST> response = service.update(category, id);
		return response;
	}
	
	/**
	 * Deletes categories by id.
	 * @param category
	 * @param id
	 * @return
	 */
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseREST> save(@PathVariable Long id){
		
		ResponseEntity<CategoryResponseREST> response = service.deleteById(id);
		return response;
	}

}
