package com.thomascantie.insa;

import java.math.BigDecimal;

import static com.thomascantie.insa.Destination.DOM_TOM;
import static com.thomascantie.insa.Destination.MC;


public abstract class Package {

	private static final double PERCENTAGE_INCREASE_MC = 0.087;
	private static final double PERCENTAGE_INCREASE_DOMTOM = 0.054;
	private static final double FIXED_DOMTOM_COSTS = 1.26;

	private int height;
	private int width;
	private int depth;
	private double weight;
	private Destination dest;

	public Package(int height, int width, int depth, double weight, Destination dest) {
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.weight = weight;
		this.dest = dest;
	}

	public double calculateShippingCost() {
		return new BigDecimal(this.calculateLocalShippingCost())
				.add(this.getIcreasedDestinationCost())
				.setScale(2, BigDecimal.ROUND_HALF_EVEN)
				.doubleValue();
	}

	protected abstract double calculateLocalShippingCost();

	protected BigDecimal getIcreasedDestinationCost() {
		if (this.dest == MC)
			return new BigDecimal(PERCENTAGE_INCREASE_MC * this.calculateLocalShippingCost());

		if (this.dest == DOM_TOM)
			return new BigDecimal(PERCENTAGE_INCREASE_DOMTOM * this.calculateLocalShippingCost() + FIXED_DOMTOM_COSTS);

		return BigDecimal.ZERO;
	}

	@Override
	public String toString() {
		return "Package of " + this.weight + " kg to " + this.dest + " ( " + this.height + "mm X " + this.width + "mm X " + this.depth + "mm )";
	}
}