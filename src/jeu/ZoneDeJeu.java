

package jeu;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import cartes.*;

public class ZoneDeJeu {
	private List<Limite> limites = new ArrayList<>();
	private List<Bataille> batailles = new ArrayList<>();
	private Collection<Borne> bornes = new ArrayList<>();
	
	
	public int donnerLimitationVitesse() {
		if (limites.isEmpty() || limites.getLast() instanceof FinLimite) {
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int kmCount = 0;
		
		// on parcourt la collection de borne déposer dans la zone
		for(Borne borne : bornes) {
			kmCount = kmCount + borne.getKm();
		}
		return kmCount;
		
	}
	
	public void deposer(Carte c) {
		if(c instanceof Borne borne) {
			bornes.add(borne);
		} else if (c instanceof Limite limite ){
			limites.add(limite);		
		} else if (c instanceof FinLimite finLimite){
			limites.add(finLimite);
		} else if (c instanceof Bataille bataille) {
			batailles.add(bataille);	
		}
	}
	

}
