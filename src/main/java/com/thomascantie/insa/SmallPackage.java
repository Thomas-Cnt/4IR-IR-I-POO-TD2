package com.thomascantie.insa;

import java.math.BigDecimal;

import static com.thomascantie.insa.Destination.MC;

public class SmallPackage extends Package {

	public SmallPackage(int height, int width, int depth, double weight, Destination dest) {
		super(height, width, depth, weight, dest);
	}

	@Override
	public double calculateLocalShippingCost() {
		BigDecimal cost = BigDecimal.ZERO;

		cost = cost.add(new BigDecimal(12.00));

		if (this.hasDestination(MC))
			cost = this.getIcreasedCostForMC(cost);

		return cost.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}
}