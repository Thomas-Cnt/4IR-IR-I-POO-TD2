package com.thomascantie.insa;

import java.math.BigDecimal;

import static com.thomascantie.insa.Destination.MC;

public class BigPackage extends Package {


	private double weightFlatRate;
	private double volumeFlatRate;

	public BigPackage(int height, int width, int depth, double weight, Destination dest) {
		super(height, width, depth, weight, dest);
		this.weightFlatRate = 21.62 * weight;
		this.volumeFlatRate = 1.43 * height * width * depth / Math.pow(100.0, 3);
	}

	@Override
	public double calculateLocalShippingCost() {
		BigDecimal cost = BigDecimal.ZERO;

		cost = cost.add(new BigDecimal(Math.max(this.weightFlatRate, this.volumeFlatRate)));

		if (this.hasDestination(MC))
			cost = this.getIcreasedCostForMC(cost);

		return cost.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

}