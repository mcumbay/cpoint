package com.dfwcomputech.cpoint.common.util;

import org.modelmapper.ModelMapper;

public class BeanMapper<D,E> {
	private Class<E> typeOfE;
	private Class<D> typeOfD;
	private ModelMapper mapper;
		
	public BeanMapper(Class<D> typeOfD,Class<E> typeOfE) {
		this.mapper = new ModelMapper();	
        this.typeOfE = typeOfE;        
        this.typeOfD = typeOfD;
        
	}

	public D toDto(E entityBean) {
		return mapper.map(entityBean,typeOfD);
	}
	
	public E toEntity(D dtoBean) {		
		return mapper.map(dtoBean,typeOfE);
		
	}
}
