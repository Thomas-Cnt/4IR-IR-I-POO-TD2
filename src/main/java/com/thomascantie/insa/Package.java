package com.thomascantie.insa;

import java.math.BigDecimal;

import static com.thomascantie.insa.Destination.DOM_TOM;
import static com.thomascantie.insa.Destination.MC;


public abstract class Package {

	private int height;
	private int width;
	private int depth;
	private double weight;
	private Destination dest;

	public Package(int height, int width, int depth, double weight) {
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.weight = weight;
		this.dest = dest;
	}

	public abstract double calculateLocalShippingCost();

	@Override
	public String toString() {
		return "Package of " + this.weight + " kg to " + this.dest + " ( " + this.height + "mm X " + this.width + "mm X " + this.depth + "mm )";
	}
}