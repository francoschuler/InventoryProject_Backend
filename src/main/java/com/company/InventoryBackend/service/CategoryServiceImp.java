package com.company.InventoryBackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.InventoryBackend.dao.ICategoryDAO;
import com.company.InventoryBackend.model.Category;
import com.company.InventoryBackend.response.CategoryResponseREST;

@Service
public class CategoryServiceImp implements ICategoryService{
	
	@Autowired
	private ICategoryDAO categoryDAO;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseREST> search() {
		
		CategoryResponseREST response = new CategoryResponseREST();
		try {
			List<Category> category = (List<Category>) categoryDAO.findAll();
			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa.");
		}catch (Exception e){
			
			response.setMetadata("ERROR", "-1", "Error al consultar las categorias.");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.OK);
		
	}

}
