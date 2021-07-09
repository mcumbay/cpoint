package com.dfwcomputech.cpoint.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

class BeanMapperUnitTest {
	
	private BeanMapper<MockDtoBean, MockEntityBean> beanMapper;
	private ModelMapper mapper;
	
	private MockDtoBean getMockDtoBean() {
		return new MockDtoBean("anyString", Integer.valueOf(10),BigDecimal.valueOf(.5));
	}
	
	private MockEntityBean getMockEntityBean() {
		return new MockEntityBean("anyString", Integer.valueOf(10),BigDecimal.valueOf(.5));
	}
	
	@BeforeEach
	private void init() {
		beanMapper = new BeanMapper<>(MockDtoBean.class,MockEntityBean.class);
		mapper = new ModelMapper();
	}
	
	@Test
	void shouldMapDtoToEntity() {
		//Arrange
		MockDtoBean myDtoBean = getMockDtoBean();		
		//Action
		MockEntityBean  myEntityBean =beanMapper.toEntity(myDtoBean);
		//MockEntityBean  myEntityBean =  mapper.map(myDtoBean, MockEntityBean.class);
		//Assert
		assertEquals(myDtoBean.getStrAttribute(), myEntityBean.getStrAttribute());
		assertEquals(myDtoBean.getIntAttribute(), myEntityBean.getIntAttribute());
		assertEquals(myDtoBean.getDecAttribute(), myEntityBean.getDecAttribute());
	}
}
