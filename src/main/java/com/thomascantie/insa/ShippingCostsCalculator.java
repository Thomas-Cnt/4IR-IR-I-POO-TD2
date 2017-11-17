package com.thomascantie.insa;

import java.math.BigDecimal;

import static com.thomascantie.insa.Destination.DOM_TOM;
import static com.thomascantie.insa.Destination.MC;

public class ShippingCostsCalculator {

	private static final double PERCENTAGE_INCREASE_MC = 0.087;
	private static final double PERCENTAGE_INCREASE_DOMTOM = 0.054;
	private static final double FIXED_DOMTOM_COSTS = 1.26;

	private static ShippingCostsCalculator instance;

	private ShippingCostsCalculator() {

	}

	public static synchronized ShippingCostsCalculator getInstance() {
		if (instance == null)
			instance = new ShippingCostsCalculator();
		return instance;
	}

	public double calculateShippingCost(Package pack, Destination destination){

		return new BigDecimal(pack.calculateLocalShippingCost())
				.add(this.getIcreasedDestinationCost(pack.calculateLocalShippingCost(), destination))
				.setScale(2, BigDecimal.ROUND_HALF_EVEN)
				.doubleValue();
	}

	private BigDecimal getIcreasedDestinationCost(double cost, Destination dest) {
		if (dest == MC)
			return new BigDecimal(PERCENTAGE_INCREASE_MC * cost);

		if (dest == DOM_TOM)
			return new BigDecimal(PERCENTAGE_INCREASE_DOMTOM * cost + FIXED_DOMTOM_COSTS);

		return BigDecimal.ZERO;
	}

}
