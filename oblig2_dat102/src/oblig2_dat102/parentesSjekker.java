package oblig2_dat102;

public class parentesSjekker {
	
	public boolean erStartParantes(char c) {
		return c == '(' || c == '{' || c == '[';
	}
	
	public boolean erSluttParantes(char c) {
		return c == ']' ||c == '}' || c == ')';
	}
	
	public boolean erParantesPar(char start, char slutt) {
		return start == '(' && slutt == ')' || start == '{' && slutt == '}' || start == '[' && slutt == ']';
	}
	
	boolean sjekkParenteser(String s) {
		  stabelADT<Character> stabel = new tabellStabel<>();

		    for (char c : s.toCharArray()) {
		        if (Character.isWhitespace(c)) { 
		            continue; // Hopp over mellomrom
		        }

		        if (erStartParantes(c)) {
		            stabel.push(c); // Legg startparentes på stabelen
		        } else if (erSluttParantes(c)) {
		            if (stabel.isEmpty() || !erParantesPar(stabel.pop(), c)) {
		                return false; // Feil rekkefølge eller manglende startparentes
		            }
		        }
		    }
		    
		    return stabel.isEmpty(); // Sjekk at alle startparenteser har matchende sluttparenteser
		}
	}
