package com.company.InventoryBackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.InventoryBackend.dao.ICategoryDAO;
import com.company.InventoryBackend.dao.IProductDAO;
import com.company.InventoryBackend.model.Category;
import com.company.InventoryBackend.model.Product;
import com.company.InventoryBackend.response.ProductResponseREST;
import com.company.InventoryBackend.util.Util;

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
	@Transactional
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

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseREST> searchById(Long id) {
		
		ProductResponseREST response = new ProductResponseREST();
		List<Product> list = new ArrayList<>();
		
		try {
			
			// Search product by id
			Optional<Product> product = productDAO.findById(id);
			if(product.isPresent()) {
				
				byte imagenDecompressed[] = Util.decompressZLib(product.get().getPicture());
				product.get().setPicture(imagenDecompressed);
				list.add(product.get());
				response.getProduct().setProducts(list);
				response.setMetadata("Respuesta OK", "00", "Producto encontrado.");
				
			}else {
				response.setMetadata("ERROR", "-1", "Producto con el id " + id + " no encontrado.");
				return new ResponseEntity<ProductResponseREST>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("ERROR", "-1", "Error al buscar producto");
			return new ResponseEntity<ProductResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseREST>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseREST> searchByName(String name) {
		
		ProductResponseREST response = new ProductResponseREST();
		List<Product> list = new ArrayList<>();
		List<Product> listAux = new ArrayList<>();
		
		try {
			
			// Search product by name
			listAux = productDAO.findByNameContainingIgnoreCase(name);
			if(listAux.size() > 0) {
				
				listAux.stream().forEach((p)->  {
					byte imagenDecompressed[] = Util.decompressZLib(p.getPicture());
					p.setPicture(imagenDecompressed);
					list.add(p);
				});
				
				
				response.getProduct().setProducts(list);
				response.setMetadata("Respuesta OK", "00", "Producto(s) encontrado(s).");
				
			}else {
				response.setMetadata("ERROR", "-1", "Producto(s) con el id no encontrados.");
				return new ResponseEntity<ProductResponseREST>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("ERROR", "-1", "Error al buscar producto(s)");
			return new ResponseEntity<ProductResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseREST>(response, HttpStatus.OK);
	}
	
}
