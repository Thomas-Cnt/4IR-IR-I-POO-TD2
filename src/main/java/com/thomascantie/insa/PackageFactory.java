package com.thomascantie.insa;

public class PackageFactory {

	private static PackageFactory instance;

	private PackageFactory() {

	}

	public static synchronized PackageFactory getInstance() {
		if (instance == null)
			instance = new PackageFactory();
		return instance;
	}

	public Package createPackage(int height, int width, int depth, double weight) {

		if (hasLittleDimensions(height, width, depth))
			return new SmallPackage(height, width, depth, weight);

		if (weight <= 1.8)
			return new MediumPackage(height, width, depth, weight);

		return new BigPackage(height, width, depth, weight);

	}

	private boolean hasLittleDimensions(int h, int w, int d) {

		if (h <= 229 && w <= 162 && d <= 25
			|| h <= 229 && d <= 162 && w <= 25
			|| w <= 229 && h <= 162 && d <= 25
			|| w <= 229 && d <= 162 && h <= 25
			|| d <= 229 && h <= 162 && w <= 25
			|| d <= 229 && w <= 162 && h <= 25)
			return true;
		return false;
	}


}
