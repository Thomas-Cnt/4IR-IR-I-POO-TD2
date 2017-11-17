package com.thomascantie.insa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static com.thomascantie.insa.Destination.FR;
import static com.thomascantie.insa.Destination.MC;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class TestShippingCost {

	private final int height;
	private final int width;
	private final int depth;
	private final double weight;
	private final String dest;
	private final String cost;

	public TestShippingCost(final int height, final int width, final int depth, final double weight, final String dest, final String cost) {
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.weight = weight;
		this.dest = dest;
		this.cost = cost;
	}

	@Test
	public void isValidShippingCost() {
		Package pack = PackageFactory.getInstance().createPackage(height, width, depth, weight);
		final double result = ShippingCostsCalculator.getInstance().calculateShippingCost(pack, Destination.valueOf(dest));

		assertThat(result).isEqualTo(Double.parseDouble(cost));
	}

	private static final Object[][] testParameters = new Object[][]{

			{ 191, 123, 18,  2.354d, "FR", "12.00" },

			{ 253, 215, 164, 1.565d, "FR", "30.39" },

			{ 653, 133, 271, 2.132d, "FR", "46.09" },

			{ 653, 331, 271, 3.650d, "FR", "83.76" },

			{ 123, 191, 18,  2.354d, "MC", "13.04" },

			{ 253, 215, 164, 1.565d, "MC", "33.03" },

			{ 653, 133, 271, 2.132d, "MC", "50.10" },

			{ 653, 331, 271, 3.650d, "MC", "91.05" },

			{ 191, 123, 18,	2.354d,	"DOM_TOM", "13.91" },

			{ 253, 215, 164, 1.565d, "DOM_TOM", "33.29" },

			{ 653, 133, 271, 2.132d, "DOM_TOM", "49.84" },

			{ 653, 331, 271, 3.650d, "DOM_TOM", "89.54" }

	};


	@Parameterized.Parameters
	public static Collection<Object[]> params() {
		return Arrays.asList(testParameters);
	}


}
