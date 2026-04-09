package jeu;
import java.util.Iterator;


import java.util.ArrayList;
import java.util.List;

import cartes.*;


// pour permettre l'itération sur les cartes
public class MainJoueur implements Iterable<Carte>{
	private List<Carte> carteMain = new ArrayList<>();
	
	public void prendre(Carte carte) {
		carteMain.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert carteMain.contains(carte) : "On ne peut pas joueur la carte si on l'a pas en main !";
		carteMain.remove(carte);
	}
	
	@Override
	public String toString() {
		//return "Contenu de la main du joueur : " + carteMain;
		// méthode avec iterator 
		return carteMain.toString();
	}

	@Override
	public Iterator<Carte> iterator() {
		// TODO Auto-generated method stub
		return carteMain.iterator();
	}

}
