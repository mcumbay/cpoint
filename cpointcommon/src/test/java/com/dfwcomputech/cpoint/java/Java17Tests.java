package com.dfwcomputech.cpoint.java;

import java.util.random.RandomGeneratorFactory;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import com.dfwcomputech.cpoint.java.jep409.Circle;
import com.dfwcomputech.cpoint.java.jep409.Square;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Java17Tests {

	/*
	 * JEP-356 Enhanced Pseudo-Random Number Generators
	 */
	@Test
	void jep356_randomNumbers() {
		RandomGeneratorFactory.all()
        .map(fac -> fac.group()+ " : " +fac.name())
        .sorted()
        .forEach(log::debug);
	}
	
	/*
	 * JEP-409: Sealed Classes
	 */
	@Test
	void jep409_sealedClasses() {
		//Arrange
		Circle circle = new Circle(2.5);
		Square square = new Square(5);
		Class<?> roundedInterface = getSuperInterface(circle);
		Class<?> poligonInterface = getSuperInterface(square);
		Class<?> shapeInterface = getSuperInterface(roundedInterface);
		//Act				
		//Assert		
		//1.Circle is not sealed		
		Assertions.assertThat(circle.getClass().isSealed()).isEqualTo(false);
		//2.Rounded is not sealed
		Assertions.assertThat(roundedInterface.isSealed()).isEqualTo(false);
		//3.Shape is sealed
		Assertions.assertThat(shapeInterface.isSealed()).isEqualTo(true);
		//4.Shape interface permits Rounded and Poligon 
		Assertions.assertThat(Arrays.asList(shapeInterface.getPermittedSubclasses()))
		  .contains(roundedInterface,poligonInterface);
	}

	@Test
	void testInterfacesReflection() {
		// Arrange
		Square square = new Square(2.5);		
		// Act
		Class<?>[] interfaces = square.getClass().getInterfaces();
		Stream.of(interfaces)
				.forEach(inter -> log.debug("Interface of {} = {}", square.getClass().getName(), inter.getName()));
	}
	
	private static Class<?> getSuperInterface(Object o){
		Class<?>[] interfaces = o.getClass().getInterfaces();
		return Stream.of(interfaces).findFirst().get();
	}
	
	private static Class<?> getSuperInterface(Class<?> c){
		Class<?>[] interfaces = c.getInterfaces();
		return Stream.of(interfaces).findFirst().get();
	}
}
