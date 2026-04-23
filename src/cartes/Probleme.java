package cartes;

public abstract class Probleme extends Carte {
	private Type type;


	protected Probleme(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	
	// Ajout du TP2
	@Override
	public int hashCode() {
		// Crée un numéro d'identité basé sur le type (ACCIDENT, CREVAISON...)
		return 31 * ((type == null) ? 0 : type.hashCode()); 
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		// On transforme l'objet pour pouvoir comparer son type
		Probleme autre = (Probleme) obj; // Mets (Botte) si tu es dans la classe Botte
		
		if (type == null) {
			if (autre.type != null) return false;
		} else if (!type.equals(autre.type)) {
			return false;
		}
		return true;
	}

}
