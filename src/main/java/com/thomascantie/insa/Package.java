package com.thomascantie.insa;

import java.math.BigDecimal;

import static com.thomascantie.insa.Destination.MC;

public class Package {

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

	public double calculateLocalShippingCost() {
		BigDecimal cost = BigDecimal.ZERO;

		if ( (height <= 229 && width <= 162) || (width <= 229 && height <= 162) && depth <= 25)
			cost = cost.add(new BigDecimal(12.00));

		else if (weight <= 1.8)
			cost = cost.add(new BigDecimal(17.59 * this.weight + 2.86));

		else
			cost = cost.add(new BigDecimal(Math.max(21.62 * weight, 1.43 * height * width * depth / Math.pow(100.0, 3))));

		if (this.dest == MC)
			cost = cost.add(new BigDecimal(0.087 * cost.doubleValue()));

		return cost.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

}
