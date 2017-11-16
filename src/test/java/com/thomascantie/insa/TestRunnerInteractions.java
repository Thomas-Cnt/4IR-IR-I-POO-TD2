package com.thomascantie.insa;

import org.junit.Test;
import org.mockito.InOrder;

import java.io.ByteArrayInputStream;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class TestRunnerInteractions {

	public static final int HEIGHT = 191;
	public static final int WIDTH = 123;
	public static final int DEPTH = 18;
	public static final double WEIGHT = 2.354;
	public static final String DOM_TOM = "DOM_TOM";

	@Test
	public void When_calculate_shipping_cost() {
		Package pack = mock(Package.class);
		PackageFactory factory = mock(PackageFactory.class);

		when(factory.createPackage(HEIGHT, WIDTH, DEPTH, WEIGHT, DOM_TOM)).thenReturn(pack);

		String info = "yes\nDOM/TOM\n191\n123\n18\n2.354\nyes\nno\n";
		System.setIn(new ByteArrayInputStream(info.getBytes()));

		new Runner(factory).run();

		InOrder inOrder = inOrder(factory, pack, pack, pack);

		inOrder.verify(factory).createPackage(HEIGHT, WIDTH, DEPTH, WEIGHT, DOM_TOM);
		inOrder.verify(pack).calculateShippingCost();
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	public void When_calculate_shipping_cost_aborded() {
		Package pack = mock(Package.class);
		PackageFactory factory = mock(PackageFactory.class);

		when(factory.createPackage(HEIGHT, WIDTH, DEPTH, WEIGHT, DOM_TOM)).thenReturn(pack);

		String info = "yes\nquit\nno\n";
		System.setIn(new ByteArrayInputStream(info.getBytes()));

		new Runner(factory).run();

		InOrder inOrder = inOrder(factory, pack);

		inOrder.verify(factory, never()).createPackage(anyInt(), anyInt(), anyInt(), anyDouble(), anyString());
		inOrder.verify(pack, never()).calculateShippingCost();
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	public void When_calculate_shipping_cost_with_unvalid_information() {
		Package pack = mock(Package.class);
		PackageFactory factory = mock(PackageFactory.class);

		when(factory.createPackage(HEIGHT, WIDTH, DEPTH, WEIGHT, DOM_TOM)).thenReturn(pack);

		String info = "yes\nDOM/TOM\n191\n123\n18\n2.354\nno\nquit\nno\n";
		System.setIn(new ByteArrayInputStream(info.getBytes()));

		new Runner(factory).run();

		InOrder inOrder = inOrder(factory, pack);

		inOrder.verify(factory).createPackage(anyInt(), anyInt(), anyInt(), anyDouble(), anyString());
		inOrder.verify(pack, never()).calculateShippingCost();
		inOrder.verifyNoMoreInteractions();
	}

}
