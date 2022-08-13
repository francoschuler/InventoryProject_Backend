package com.company.InventoryBackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.InventoryBackend.model.Product;


public interface IProductDAO extends CrudRepository<Product, Long> {
	
	@Query("SELECT p from Product p WHERE p.name LIKE %?1%")
	public List<Product> findByNameLike(String name);
	
	public List<Product> findByNameContainingIgnoreCase(String name);

}
