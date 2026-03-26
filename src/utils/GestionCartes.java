package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	
	// a)
	
	// Version 1
	public static <T> T extraireV1(List<T> lst) {
		Random random = new Random();
		int indiceAlea = random.nextInt(lst.size());
		
		// on recup l'elt grâce au remove car en remove il renvoie l'elt
		T elt = lst.remove(indiceAlea);
		return elt;
		
	}
	
	// Version 2
	public static <T> T extraireV2(List<T> lst) {
		Random random = new Random();
		int indiceAlea = random.nextInt(lst.size());
		ListIterator<T> iterateur = lst.listIterator();
		T elt = null;
		
		// on fait avancer l'iterateur jusqu'a l'indice qu'on a choisi
		for (int i = 0; i <= indiceAlea; i++) {
            elt = iterateur.next();
        }
		
		iterateur.remove();
		
		return elt;
	}
	

	// b) 
	// lst doit etre vide (normalement)
	public static <T> List<T> melanger(List<T> lst) {
		List<T> lstMelange = new ArrayList<>();
		while(!lst.isEmpty()) {
			lstMelange.add(extraireV1(lst));
		}
		return lstMelange;
	}
	
	
	// c) 
	public static <T> boolean verifierMelange(List<T> lst1, List<T> lst2) {
		for (ListIterator<T> iterateur = lst1.listIterator(); iterateur.hasNext();) {
			T elt = iterateur.next();
			int colecL1 = Collections.frequency(lst1, elt);
			int colecL2 = Collections.frequency(lst2, elt);
			if(colecL1 != colecL2) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
}
