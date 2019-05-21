package edu.cwru.jfh86_jlk187.parser;

import static org.junit.Assert.*;  
import org.junit.Test;

public class TestAbstractToken
{
	Variable a = Variable.build("a");
	Connector close = Connector.build(TerminalSymbol.CLOSE);

	// tests whether the param is equal to calling object
	@Test
	public void testMatches()
	{
		assertFalse(a.matches(TerminalSymbol.PLUS));
		assertTrue(a.matches(TerminalSymbol.VARIABLE));
		assertFalse(close.matches(TerminalSymbol.DIVIDE));
		assertTrue(close.matches(TerminalSymbol.CLOSE));
	}
}
