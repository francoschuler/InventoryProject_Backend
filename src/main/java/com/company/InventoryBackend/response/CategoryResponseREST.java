package com.company.InventoryBackend.response;

import lombok.Getter;
import lombok.Setter;

//Para evitar generar getters y setters del constructor del padre
@Getter
@Setter
public class CategoryResponseREST extends ResponseRest{
	private CategoryResponse categoryResponse = new CategoryResponse();
}
