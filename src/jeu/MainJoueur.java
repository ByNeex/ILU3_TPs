package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.*;

public class MainJoueur {
	private List<Carte> carteMain = new ArrayList<>();
	
	public void prendre(Carte carte) {
		carteMain.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert carteMain.contains(carte) : "On ne peut pas joueur la carte si on l'a pas en main !";
		carteMain.remove(carte);
	}
	

}
