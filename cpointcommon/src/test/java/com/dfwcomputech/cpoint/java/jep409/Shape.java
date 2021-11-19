package com.dfwcomputech.cpoint.java.jep409;

public sealed interface Shape permits Polygon, Rounded{
	double getPerimeter();
	double getArea();
}
