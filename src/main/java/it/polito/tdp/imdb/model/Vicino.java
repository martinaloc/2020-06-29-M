package it.polito.tdp.imdb.model;

public class Vicino implements Comparable<Vicino>{
	
	private Director d2;
	
	private int peso;

	public Vicino( Director d2, int peso) {
		
		this.d2 = d2;
		this.peso = peso;
	}

	

	public Director getD2() {
		return d2;
	}

	public int getPeso() {
		return peso;
	}



	@Override
	public int compareTo(Vicino o) {
		
		return -(this.peso-o.getPeso());
	}



	@Override
	public String toString() {
		return d2 + " " + peso ;
	}

}
