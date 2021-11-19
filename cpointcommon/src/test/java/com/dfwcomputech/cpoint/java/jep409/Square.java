package com.dfwcomputech.cpoint.java.jep409;

public final class Square implements Polygon{

	private int sides=4;
	private double length;
	
	public Square(double length){
		this.length=length;
	}
	
	@Override
	public double getPerimeter() {		
		return length*sides;
	}

	@Override
	public double getArea() {
		return Math.pow(length, 2);
	}
	
	@Override
	public int getSides() {	
		return sides;
	}

}
