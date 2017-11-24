package com.thomascantie.insa;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PackageFactory {

	private static final int FIRST_LIMIT = 25;
	private static final int SECOND_LIMIT = 162;
	private static final int THIRD_LIMIT = 229;
	private static final double WEIGHT_LIMIT = 1.8;
	
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

		if (weight <= WEIGHT_LIMIT)
			return new MediumPackage(height, width, depth, weight);

		return new BigPackage(height, width, depth, weight);

	}

	private boolean hasLittleDimensions(int h, int w, int d) {
		List<Integer> dimensions = Arrays.asList(h, w, d);
		Collections.sort(dimensions);
		return dimensions.get(0) <= FIRST_LIMIT &&
				dimensions.get(1) <= SECOND_LIMIT &&
				dimensions.get(2) <= THIRD_LIMIT;
	}


}
