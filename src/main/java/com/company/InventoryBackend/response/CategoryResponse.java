package com.company.InventoryBackend.response;

import java.util.List;

import com.company.InventoryBackend.model.Category;

import lombok.Data;


@Data
public class CategoryResponse {
	
	private List<Category> category;
	
}
