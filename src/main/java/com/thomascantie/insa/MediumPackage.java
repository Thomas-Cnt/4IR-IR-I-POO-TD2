package com.thomascantie.insa;

import java.math.BigDecimal;

import static com.thomascantie.insa.Destination.MC;

public class MediumPackage extends Package {

	private static final double FIXED_COSTS = 2.86;
	private static final double UNIT_WEIGHT_COST = 17.59;

	private double weightFlatRate;

	public MediumPackage(int height, int width, int depth, double weight, Destination dest) {
		super(height, width, depth, weight, dest);
		this.weightFlatRate = UNIT_WEIGHT_COST * weight;
	}

	@Override
	protected double calculateLocalShippingCost() {
		return this.weightFlatRate + FIXED_COSTS;
	}

}