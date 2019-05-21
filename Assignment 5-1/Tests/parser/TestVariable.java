package edu.cwru.jfh86_jlk187.parser;

import static org.junit.Assert.*; 
import org.junit.Test;

public class TestVariable
{

	Variable a = Variable.build("a");
	
	// returns the TerminalSymbol value of Variable
	@Test
	public void testGetType() {
		assertEquals(TerminalSymbol.VARIABLE, a.getType());
	}
	
	// returns the variable name representation
	@Test
	public void testGetRepresentation() {
		assertEquals("a", a.getRepresentation());
	}
	
	// testing the get method of Cache.java
	@Test
	public void testBuild() {
		assertEquals(a, Variable.build("a"));
		//assertThrows(NullPointerException.class, () -> {Variable.build(null);});
	}
	
	// returns same as getRepresentation
	@Test
	public void testToString() {
		assertEquals("a", a.toString());
	}

}
