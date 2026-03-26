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
	public boolean equals(Object obj) {
		Object recupClass = obj.getClass();
		
		if(obj!=null && recupClass.equals(this.getClass())) {
			Probleme probleme = (Probleme) obj;
			return getType().equals(probleme.getType());
		
		}
		return false;
	}
	

}
