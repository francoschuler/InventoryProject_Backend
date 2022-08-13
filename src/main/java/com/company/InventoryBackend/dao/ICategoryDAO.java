package com.company.InventoryBackend.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.InventoryBackend.model.Category;

public interface ICategoryDAO extends CrudRepository<Category, Long>{
	
}
