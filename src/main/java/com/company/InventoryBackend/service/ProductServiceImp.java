package com.company.InventoryBackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.InventoryBackend.dao.ICategoryDAO;
import com.company.InventoryBackend.dao.IProductDAO;
import com.company.InventoryBackend.model.Category;
import com.company.InventoryBackend.model.Product;
import com.company.InventoryBackend.response.ProductResponseREST;

@Service
public class ProductServiceImp implements IProductService {
	
	private ICategoryDAO categoryDAO;
	private IProductDAO productDAO;

	public ProductServiceImp(ICategoryDAO categoryDAO, IProductDAO productDAO) {
		super();
		this.categoryDAO = categoryDAO;
		this.productDAO = productDAO;
	}

	@Override
	public ResponseEntity<ProductResponseREST> save(Product product, Long categoryId) {
		
		ProductResponseREST response = new ProductResponseREST();
		List<Product> list = new ArrayList<>();
		
		try {
			
			// Search category to set in the product object
			Optional<Category> category = categoryDAO.findById(categoryId);
			if(category.isPresent()) {
				product.setCategory(category.get());
			}else {
				response.setMetadata("ERROR", "-1", "Categoria con el id " + categoryId + " no encontrada.");
				return new ResponseEntity<ProductResponseREST>(response, HttpStatus.NOT_FOUND);
			}
			
			// Save the product
			
			Product productSaved = productDAO.save(product);
			if (productSaved != null) {
				// Se guardo el producto
				list.add(productSaved);
				response.getProduct().setProducts(list);
				response.setMetadata("Respuesta OK", "00", "Producto guardado correctamente");
			}else {
				response.setMetadata("ERROR", "-1", "Producto no guardado.");
				return new ResponseEntity<ProductResponseREST>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("ERROR", "-1", "Error al guardar producto");
			return new ResponseEntity<ProductResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseREST>(response, HttpStatus.OK);
		
	}
	
}
