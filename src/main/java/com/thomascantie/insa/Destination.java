package com.thomascantie.insa;

public enum Destination {

	FR("FR") ,
	MC("MC") ,
	DOM_TOM("DOM/TOM") ;

	private String text;

	Destination(final String st) {
		this.text = st;
	}

	@Override
	public String toString() {
		return this.text;
	}
}
