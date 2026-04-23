package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cartes.*;

public class Joueur {
	private ZoneDeJeu zoneJoueur = new ZoneDeJeu();
	private String nom;
	private MainJoueur main = new MainJoueur();
	
	public Joueur(String nom) {
		this.nom = nom;
	}
	
	
	public String getNom() {
		return nom;
	}
	
	public MainJoueur getMain() {
		return main;
	}
	
	
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		
		Carte fstCarte = sabot.piocher();
		this.donner(fstCarte);
		return fstCarte;
		
	}
	
	public int donnerKmParcourus() {
		return zoneJoueur.donnerKmParcourus();
	}
	
	public void deposer(Carte c) {
		zoneJoueur.deposer(c);
	}
	
	@Override
	public int hashCode() {
		return nom.hashCode();
	}


	@Override
	public String toString() {
		return (" Le joueur s'appelle : " + nom);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Joueur joueur) {
			return nom.equals(joueur.getNom());
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneJoueur.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> coupsValides = new HashSet<>();
		for (Iterator<Joueur> iteratorJ = participants.iterator(); iteratorJ.hasNext();) {
			Joueur joueur = iteratorJ.next();
			for (Iterator<Carte> iteratorC = main.iterator();iteratorC.hasNext();) {
				Carte carte = iteratorC.next();
				Coup coup = new Coup(this,carte,joueur);
				if(coup.estValide()) {
					coupsValides.add(coup);
				}
			}
		}
		return coupsValides;
	}

	public Set<Coup> coupsDefausse(){
		Set<Coup> coupsDefausse = new HashSet<>();
		for (Iterator<Carte> iterator = main.iterator();iterator.hasNext();) {
			Carte carte = iterator.next();
			Coup coup = new Coup(this,carte,null);
			coupsDefausse.add(coup);
		}
		return coupsDefausse;
	}
	
	
	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupsValides = coupsPossibles(participants);
		if (!coupsValides.isEmpty()) {
			return choixCoupAleatoire(coupsValides);
		}
		Set<Coup> coupsDefausse = coupsDefausse();
		return choixCoupAleatoire(coupsDefausse);
	}
	
	
	private Coup choixCoupAleatoire(Set<Coup> coups) {
		List<Coup> listCoups = new ArrayList<>();
		listCoups.addAll(coups);
		Random random = new Random();
		int randomNum = random.nextInt(listCoups.size());
		Coup coup = listCoups.get(randomNum);
		listCoups.remove(randomNum);
		return coup;
	}
	
	public String afficherEtatJoueur() {
		StringBuilder sb = new StringBuilder();
		sb.append("Bottes :");
		Set<Botte> bottes = zoneJoueur.getBottes();
		for(Botte botte : bottes) {
			sb.append(botte.toString());
			sb.append(", ");
		}
		sb.append('\n');
		
		sb.append("Limite ? : ");
		if (zoneJoueur.getLimites().isEmpty()) {
			sb.append("vide");
		} else {
			sb.append(zoneJoueur.getLimites().get(0).equals(new DebutLimite()));
		}
		sb.append('\n');
		
		sb.append("Sommet de la pile de bataille ? : ");
		if (zoneJoueur.getBatailles().isEmpty()) {
			sb.append("vide");
		} else {
			sb.append(zoneJoueur.getBatailles().get(0).toString());
		}
		sb.append('\n');
		
		//sb.append("Km parcourus : "+donnerKmParcourus());
		
		sb.append(main.toString());
		return sb.toString();
	}
	
}
