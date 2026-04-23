

package jeu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.*;

public class ZoneDeJeu {
	private List<Limite> limites = new ArrayList<>();
	private List<Bataille> batailles = new ArrayList<>();
	private Collection<Borne> bornes = new ArrayList<>();
	private Set<Botte> bottes = new HashSet<>();
	
	
	//ajout de getters pour la classe joeuur
	
	public Set<Botte> getBottes() {
		return bottes;
	}
	
	public List<Limite> getLimites() {
		return limites;
	}
	
	public List<Bataille> getBatailles() {
		return batailles;
	}
	
	public boolean estPrioritaire() {
		return bottes.contains(Cartes.PRIORITAIRE);
	}
	
	
	public int donnerLimitationVitesse() {
		if (limites.isEmpty() || limites.getLast() instanceof FinLimite || estPrioritaire()) {
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
		} else if (c instanceof Bataille bataille) {
			batailles.add(bataille);	
		} else if (c instanceof Botte botte) {
			bottes.add(botte);
		}
	}
	
	private boolean estDepotFeuVertAutorise() {
		
		if(bottes.contains(Cartes.PRIORITAIRE)) {
			return false;
		}
				
		if(batailles.isEmpty() || 
				(batailles.getLast().equals(Cartes.FEU_ROUGE)) || 
				(batailles.getLast() instanceof Parade && !batailles.getLast().equals(Cartes.FEU_VERT)) ||
				(batailles.getLast() instanceof Attaque && bottes.contains(new Botte(batailles.getLast().getType()))) ) {
			
			return true;
		}
				
		return false;
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		if (!this.peutAvancer()) {
			return false;
		}
		
		if (borne.getKm() > donnerLimitationVitesse()) {
			return false;
		}
		
		
		if (donnerKmParcourus() + borne.getKm() > 1000) {
			return false;
		}
		
		return true;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		
		if(bottes.contains(Cartes.PRIORITAIRE)) {
			return false;
		}
		
		if(limite instanceof DebutLimite) {
			if(limites.isEmpty() || limites.getLast() instanceof FinLimite) {
				return true;
			}
		}
		
		if(limite instanceof FinLimite) {
			if(!limites.isEmpty() && limites.getLast() instanceof DebutLimite){
				return true;
			}
		}
		return false;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		
		if (bottes.contains(new Botte(bataille.getType()))) {
			return false;
		}
		
		if(bataille instanceof Attaque && this.peutAvancer() ) {
			return true;
		}
		
		if (bataille instanceof Parade) {
			
			// cas a)
			if(bataille.equals(Cartes.FEU_VERT)) {
				return this.estDepotFeuVertAutorise();
			}
			
			// cas b)
			if(!batailles.isEmpty()) {
				if(batailles.getLast() instanceof Attaque && bataille.getType().equals(batailles.getLast().getType())) {
					return true;
				}
			}
		}
		
		return false;

	}
	
	
	// Les méthode d'autorisation publique
	public boolean peutAvancer() {
		if(!batailles.isEmpty() && batailles.getLast().equals(Cartes.FEU_VERT)) {
			return true;
		}
		
		if (!estPrioritaire()) {
			return false; 
		}
		
		if (batailles.isEmpty()) {
			return true;
		}
		
		if (batailles.getLast() instanceof Parade) {
			return true;
		}
		
		if (batailles.getLast() instanceof Attaque) {
			
			if (batailles.getLast().equals(Cartes.FEU_ROUGE)) { 
				return true;
			}
			Botte botteProtectrice = new Botte(batailles.getLast().getType());
			
			if (bottes.contains(botteProtectrice)) {
				return true;
			}
		}
		
		return false;
	}
	

	public boolean estDepotAutorise(Carte carte) {
		if (carte != null && carte instanceof Botte) {
			return true; 
		} else if (carte != null && carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		} else if (carte != null && carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		} else if (carte != null && carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		}
		return false;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ZoneDeJeu zone) {
			return limites.equals(zone.limites) && bornes.equals(zone.bornes) && batailles.equals(zone.batailles)
					&& bottes.equals(zone.bottes);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 17 * (limites.hashCode() + bornes.hashCode() + batailles.hashCode() + bottes.hashCode());
	}
	

}
