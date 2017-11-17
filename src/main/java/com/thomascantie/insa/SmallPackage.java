package com.thomascantie.insa;


import java.math.BigDecimal;

public class SmallPackage extends Package {

	public static final double BASIC_COST = 12.00;

	public SmallPackage(int height, int width, int depth, double weight) {
		super(height, width, depth, weight);
	}

	@Override
	public double calculateLocalShippingCost() {
		return BASIC_COST;
	}
}