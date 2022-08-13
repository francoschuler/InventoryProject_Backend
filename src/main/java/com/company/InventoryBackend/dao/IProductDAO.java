package com.company.InventoryBackend.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.InventoryBackend.model.Product;


public interface IProductDAO extends CrudRepository<Product, Long> {

}
