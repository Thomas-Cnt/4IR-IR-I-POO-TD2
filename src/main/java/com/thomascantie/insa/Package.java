package com.thomascantie.insa;

import java.math.BigDecimal;

import static com.thomascantie.insa.Destination.MC;

public abstract class Package {

	private static final double PERCENTAGE_INCREASE = 0.087;

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

	public abstract double calculateLocalShippingCost();

	public BigDecimal getIcreasedCostForMC(BigDecimal cost) {
		return cost.add(new BigDecimal(PERCENTAGE_INCREASE * cost.doubleValue()));
	}

	public boolean hasDestination(Destination dest) {
		return this.dest == dest;
	}

}