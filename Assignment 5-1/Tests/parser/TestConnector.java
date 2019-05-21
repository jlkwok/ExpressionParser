package edu.cwru.jfh86_jlk187.parser;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestConnector
{

	Connector c = Connector.build(TerminalSymbol.MINUS);
	
	// returns enum type of given connector
	// implicitly tests build() method because the object is being created
	@Test
	public void testGetType() {
		assertEquals(TerminalSymbol.MINUS, c.getType());
	}
	
	// testing for null and illegal exceptions
	@Test
	public void testBuild() {
		//assertThrows(IllegalArgumentException.class, () -> Connector.build(TerminalSymbol.VARIABLE));
	}
	
	// returns string representation of terminalSymbol type
	@Test
	public void testToString()
	{
		Connector minus = Connector.build(TerminalSymbol.MINUS);
		Connector plus = Connector.build(TerminalSymbol.PLUS);
		Connector times = Connector.build(TerminalSymbol.TIMES);
		Connector divide = Connector.build(TerminalSymbol.DIVIDE);
		Connector open = Connector.build(TerminalSymbol.OPEN);
		Connector close = Connector.build(TerminalSymbol.CLOSE);
		assertEquals("-", minus.toString());
		assertEquals("+", plus.toString());
		assertEquals("*", times.toString());
		assertEquals("/", divide.toString());
		assertEquals("(", open.toString());
		assertEquals(")", close.toString());
	}
}
