package com.company.InventoryBackend.response;

import java.util.List;

import com.company.InventoryBackend.model.Product;

import lombok.Data;

@Data
public class ProductResponse {
	List<Product> products;
}
