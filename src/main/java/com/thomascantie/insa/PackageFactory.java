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

		if ((height <= 229 && width <= 162) || (width <= 229 && height <= 162) && depth <= 25)
			return new SmallPackage(height, width, depth, weight);

		if (weight <= 1.8)
			return new MediumPackage(height, width, depth, weight);

		return new BigPackage(height, width, depth, weight);

	}


}
