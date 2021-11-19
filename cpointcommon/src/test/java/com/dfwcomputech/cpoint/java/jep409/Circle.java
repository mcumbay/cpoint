package com.dfwcomputech.cpoint.java.jep409;

public final class Circle implements Rounded{

	private double radius;
	
	public Circle(double radius){
		this.radius=radius;
	}
	@Override
	public double getPerimeter() {		
		return 2*Math.PI*radius;
	}

	@Override
	public double getArea() {		
		return Math.PI*Math.pow(radius, 2);
	}

}
