package oblig2_dat102;

import java.util.Random;

public class oppgave1 {

	public static void sorter(int tabell[]){
		
		int n = tabell.length;
		int minste = 0; 
		for(int i = 1; i < n; i++) {
			if(tabell[i] < tabell[minste]) {
				minste = i; 
				// finn minste, setter den først
			}
		}
		
		int temp = tabell[minste];
		tabell[minste] = tabell[0];
		tabell[0] = temp;
		// bytter minste til første posisjon (0)
		
		for(int i = 1; i < n-1; i += 2) {
			int forste = tabell[i];
			int andre = tabell[i+1];
			// legger til at to sorteres samtidig
			
			if(forste > andre) {
				int tmp = forste; 
				forste = andre;
				andre = tmp; 
				// sjekker om første er større enn andre, bytter de så om
			}
			
			int j = i-1;
			
			while(tabell[j] > andre) {
				tabell[j+2] = tabell[j]; // flytter den to plasser
				j--;
			}
			tabell[j+2] = andre;
			
			while(tabell[j] > forste) {
				tabell[j+1] = tabell[j];
				j--;
			}
			tabell[j+1] = forste;
		}
		
		if(n%2 == 1) { // hvis n er et oddetall
			int siste = tabell[n-1];
			int j = n-2;
			while(tabell[j] > siste) {
				tabell[j+1] = tabell[j];
				j--;
			}
			tabell[j+1] = siste;
		}
	}
	
	public static void main(String[]args) {
		
		int n = 20000;
		Random tilfeldig = new Random();
		int antall = 10;
		int [][]tabell = new int[antall][n];
		
		for(int i = 0; i < antall; i++) {
			for(int j = 0; j < n; j++) {
				
			
			tabell[i][j] = tilfeldig.nextInt();
			
			}
		}
			for(int i = 0; i < antall; i++) {
				sorter(tabell[i]);
			
		}
		long startTid = System.nanoTime();
		long sluttTid = System.nanoTime();
		
		
	System.out.println(sluttTid - startTid / 1.0e9); // får i sek
}
}