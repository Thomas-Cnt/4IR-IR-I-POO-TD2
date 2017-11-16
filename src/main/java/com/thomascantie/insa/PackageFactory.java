package com.thomascantie.insa;

public class PackageFactory {

	public static Package createPackage(int height, int width, int depth, double weight, String dest) {

		if ((height <= 229 && width <= 162) || (width <= 229 && height <= 162) && depth <= 25)
			return new SmallPackage(height, width, depth, weight, Destination.valueOf(dest));

		if (weight <= 1.8)
			return new MediumPackage(height, width, depth, weight, Destination.valueOf(dest));

		return new BigPackage(height, width, depth, weight, Destination.valueOf(dest));

	}


}
