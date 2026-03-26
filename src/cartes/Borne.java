package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		this.km = km;
	}
	
	@Override
	public String toString() {
		return km + "KM";
	}
	
	// Ajout du TP2
	@Override
	public boolean equals(Object obj) {
		Borne recupBorne = (Borne) obj;
		return this.km == recupBorne.km;
	}

}
