package testsFonctionnels;

import jeu.*;

import java.util.ArrayList;
import java.util.List;

public class TestJeu {
	
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Joueur j1 = new Joueur("Jack");
		Joueur j2 = new Joueur("Bill");
		Joueur j3 = new Joueur("Luffy"
				+ "");
		
		List<Joueur> liste = new ArrayList<>();
		liste.add(j1);
		liste.add(j2);
		liste.add(j3);
		
		jeu.inscrire(liste);
		jeu.distribuerCartes();
		
		for(Joueur j : liste) {
			System.out.println(j.getMain());
		}
		
		System.out.println(jeu.jouerTour(j1));
		System.out.println(jeu.jouerTour(j2));
		System.out.println(jeu.jouerTour(j3));
		
		//System.out.println(jeu.lancer());
	}
	
	
	
	
	
}