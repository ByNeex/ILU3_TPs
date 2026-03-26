package cartes;

public abstract class Carte {
	
	
	// Ajout du TP2
	@Override
	public boolean equals(Object obj) {
		
		// évite le "point point"
		Object recupClass = this.getClass();
		
		
		return obj != null &&  recupClass.equals(obj.getClass());
	}

}
