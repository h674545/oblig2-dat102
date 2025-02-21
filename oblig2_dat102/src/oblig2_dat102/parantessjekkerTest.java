package oblig2_dat102;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class parantessjekkerTest {

	 @Test
	    public void testKorrekteParenteser() {
	parentesSjekker sjekker = new parentesSjekker();
	
	assertTrue(sjekker.sjekkParenteser("( { [ ] } )"));
	 }
	@Test
		public void testFeilParanteser() {
		
		parentesSjekker sjekker = new parentesSjekker();
		
		assertFalse(sjekker.sjekkParenteser("({[]}"));
		assertFalse(sjekker.sjekkParenteser("{[]})"));
		assertFalse(sjekker.sjekkParenteser("({[})"));
	}
		
	@Test
		public void testKorrektePar() {
		
		parentesSjekker sjekker = new parentesSjekker();
		
		assertTrue(sjekker.sjekkParenteser("[]"));
		assertTrue(sjekker.sjekkParenteser("{}"));
		assertTrue(sjekker.sjekkParenteser("()"));
		
	}
	}