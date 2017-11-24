package com.thomascantie.insa;

import org.junit.Test;
import org.mockito.InOrder;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;

public class TestRunnerInteractions {

	private static final int HEIGHT = 191;
	private static final int WIDTH = 123;
	private static final int DEPTH = 18;
	private static final double WEIGHT = 2.354;
	private static final String DOM_TOM = "DOM_TOM";

	@Test
	public void When_calculate_shipping_cost() {
		Package pack = mock(Package.class);
		ShippingCostsCalculator calculator = mock(ShippingCostsCalculator.class);
		PackageFactory factory = mock(PackageFactory.class);

		when(factory.createPackage(HEIGHT, WIDTH, DEPTH, WEIGHT)).thenReturn(pack);

		String info = "yes\nDOM/TOM\n191\n123\n18\n2.354\nyes\nno\n";
		System.setIn(new ByteArrayInputStream(info.getBytes()));

		new Runner(factory, calculator).run();

		InOrder inOrder = inOrder(factory, calculator);

		inOrder.verify(factory).createPackage(HEIGHT, WIDTH, DEPTH, WEIGHT);
		inOrder.verify(calculator).calculateShippingCost(pack, Destination.valueOf(DOM_TOM));
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	public void When_calculate_shipping_cost_aborded() {
		Package pack = mock(Package.class);
		ShippingCostsCalculator calculator = mock(ShippingCostsCalculator.class);
		PackageFactory factory = mock(PackageFactory.class);

		when(factory.createPackage(HEIGHT, WIDTH, DEPTH, WEIGHT)).thenReturn(pack);

		String info = "yes\nquit\nno\n";
		System.setIn(new ByteArrayInputStream(info.getBytes()));

		new Runner(factory, calculator).run();

		InOrder inOrder = inOrder(factory, calculator);

		inOrder.verify(factory, never()).createPackage(HEIGHT, WIDTH, DEPTH, WEIGHT);
		inOrder.verify(calculator, never()).calculateShippingCost(pack, Destination.valueOf(DOM_TOM));
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	public void When_calculate_shipping_cost_with_unvalid_information() {
		Package pack = mock(Package.class);
		ShippingCostsCalculator calculator = mock(ShippingCostsCalculator.class);
		PackageFactory factory = mock(PackageFactory.class);

		when(factory.createPackage(HEIGHT, WIDTH, DEPTH, WEIGHT)).thenReturn(pack);

		String info = "yes\nDOM/TOM\n191\n123\n18\n2.354\nno\nquit\nno\n";
		System.setIn(new ByteArrayInputStream(info.getBytes()));

		new Runner(factory, calculator).run();

		InOrder inOrder = inOrder(factory, calculator);

		inOrder.verify(factory).createPackage(HEIGHT, WIDTH, DEPTH, WEIGHT);
		inOrder.verify(calculator, never()).calculateShippingCost(pack, Destination.valueOf(DOM_TOM));
		inOrder.verifyNoMoreInteractions();
	}

}
