package com.company.InventoryBackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseREST> searchById(Long id) {
		
		CategoryResponseREST response = new CategoryResponseREST();
		List<Category> list = new ArrayList<>();
		
		try {

			Optional<Category> category = categoryDAO.findById(id);
			
			if(category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta OK", "00", "Categoria encontrada.");
			} else {
				response.setMetadata("ERROR", "-1", "Categoria con el id " + id + " no encontrada.");
				return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e){
			
			response.setMetadata("ERROR", "-1", "Error al consultar las categorias por el id " + id + ".");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.OK);
	}

	@Override
	@Transactional()
	public ResponseEntity<CategoryResponseREST> save(Category category) {
		
		CategoryResponseREST response = new CategoryResponseREST();
		List<Category> list = new ArrayList<>();
		
		try {

			Category categorySaved = categoryDAO.save(category);
			if(categorySaved != null) {
				list.add(categorySaved);
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta OK", "00", "Categoria guardada correctamente.");
			}else {
				response.setMetadata("ERROR", "-1", "Categoria no guardada");
				return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e){
			
			response.setMetadata("ERROR", "-1", "Error al guardar la categoria.");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.OK);
	}

}
