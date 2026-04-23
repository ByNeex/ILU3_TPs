package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

import java.util.Objects;

public class Coup {
	private Joueur joueurCourant;
	private Carte carteJouee;
	private Joueur joueurCible; 

	public Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
		this.joueurCourant = joueurCourant;
		this.carteJouee = carteJouee;
		this.joueurCible = joueurCible;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Carte getCarteJouee() {
		return carteJouee;
	}

	public Joueur getJoueurCible() {
		return joueurCible;
	}

	public boolean estValide() {
		if (joueurCible == null) {
			return false; 
		}
		
		if (!joueurCible.estDepotAutorise(carteJouee)) {
			return false;
		}

		boolean estAttaqueOuLimite = (carteJouee instanceof Attaque) || (carteJouee instanceof DebutLimite);

		if (estAttaqueOuLimite) {
			return !joueurCourant.equals(joueurCible);
		} else {

			return joueurCourant.equals(joueurCible);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (joueurCible == null) {
			sb.append("defausse la carte ");
			sb.append(carteJouee.toString());
		} else {
			sb.append("depose la carte ");
			sb.append(carteJouee.toString());
			sb.append(" dans la zone de jeu de ");
			sb.append(joueurCible.toString());
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(carteJouee, joueurCible, joueurCourant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Coup other = (Coup) obj;
		return Objects.equals(carteJouee, other.carteJouee) 
				&& Objects.equals(joueurCible, other.joueurCible)
				&& Objects.equals(joueurCourant, other.joueurCourant);
	}
}