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
	public double calculateLocalShippingCost() {
		BigDecimal cost = BigDecimal.ZERO;

		cost = cost.add(new BigDecimal(this.weightFlatRate + FIXED_COSTS));

		if (this.hasDestination(MC))
			cost = this.getIcreasedCostForMC(cost);

		return cost.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

}