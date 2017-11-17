package com.thomascantie.insa;

import java.math.BigDecimal;
import java.util.Scanner;

public class Runner {

	private PackageFactory factory;
	private ShippingCostsCalculator calculator;

	public Runner(PackageFactory factory, ShippingCostsCalculator calculator) {
		this.factory = factory;
		this.calculator = calculator;
	}

	public void run() {

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("************************************");
		System.out.println("***** Shipping cost calculator *****");
		System.out.println("************************************");
		System.out.println();

		String answer;

		do {
			System.out.print("\nDo you have any package to send ? (yes/no) : ");
			answer = sc.nextLine().toLowerCase().trim();

			switch (answer) {
				case "yes":

					String check;
					Package pack = null;
					boolean quit = false;
					String destination;

					do {

						System.out.println("\nPlease fill in the information of your package. Enter \"quit\" to abord the execution.\n");

						do {
							System.out.print("- destination ( FR | MC | DOM/TOM ) : ");
							destination = sc.nextLine().toUpperCase().trim();
						} while (!destination.matches("(QUIT)|(FR)|(MC)|(DOM/TOM)"));

						destination = destination.replace('/', '_');

						if (destination.equals("QUIT")) {
							quit = true;
							break;
						}

						String sHeight;
						do {
							System.out.print("- height (in millimeters) : ");
							sHeight = sc.nextLine().toLowerCase().trim();
						} while (!sHeight.matches("(quit)|[0-9]+"));

						if (sHeight.equals("quit")) {
							quit = true;
							break;
						}

						int height = Integer.parseInt(sHeight);

						String sWidth;
						do {
							System.out.print("- width (in millimeters) : ");
							sWidth = sc.nextLine().toLowerCase().trim();
						} while (!sWidth.matches("(quit)|[0-9]+"));

						if (sWidth.equals("quit")) {
							quit = true;
							break;
						}

						int width = Integer.parseInt(sWidth);

						String sDepth;
						do {
							System.out.print("- depth (in millimeters) : ");
							sDepth = sc.nextLine().toLowerCase().trim();
						} while (!sDepth.matches("(quit)|[0-9]+"));

						if (sDepth.equals("quit")) {
							quit = true;
							break;
						}

						int depth = Integer.parseInt(sDepth);

						String sWeight;
						do {
							System.out.print("- weight (in kilograms) : ");
							sWeight = sc.nextLine().toLowerCase().trim();
						} while (!sWeight.matches("(quit)|[0-9]+(.[0-9]+)?"));

						if (sWeight.equals("quit")) {
							quit = true;
							break;
						}

						double weight = Double.parseDouble(sWeight);

						pack = this.factory.createPackage(height, width, depth, weight);
						System.out.println("\nLet's check those information.");
						System.out.println(pack);
						System.out.print("\nAre those information correct ? (yes/no) : ");
						check = sc.nextLine().toLowerCase().trim();

					} while(!check.equals("yes"));

					if (quit)
						break;

					System.out.print("\nComputing");
					for (int i = 0; i < 3; i++) {
						try {
							Thread.sleep(500);
							System.out.print(".");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					BigDecimal shippingCost = new BigDecimal(
							this.calculator.calculateShippingCost(pack, Destination.valueOf(destination)))
									.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					System.out.printf("\nFor shipping this package, the shipping costs amount %.2f €\n", shippingCost.doubleValue());

					break;
				case "no":
					break;
				default:
					System.out.printf(">>> Error ! Just say \"yes\" or \"no\". Found \"%s\".\n", answer);
					break;
			}

		} while (!answer.toLowerCase().trim().equals("no"));

		System.out.println();
		System.out.println("***********");
		System.out.println("*** BYE ***");
		System.out.println("***********");
		System.out.println();

		sc.close();

	}

}
