package edu.cwru.jfh86_jlk187.parser;

import static org.junit.Assert.*;  
import java.util.*;
import org.junit.Test;

public class NonTerminalSymbolTest {

	@Test
	public void testParse()
	{
		List<Token> tokens = new ArrayList<>();
		Variable v1 = Variable.build("x");
		Connector c1 = Connector.build(TerminalSymbol.PLUS);
		Variable v2 = Variable.build("y");
		Connector c2 = Connector.build(TerminalSymbol.DIVIDE);
		Variable v3 = Variable.build("z");

		tokens.add(v1);
		tokens.add(c1);
		tokens.add(v2);
		tokens.add(c2);
		tokens.add(v3);
		
		System.out.println("testParse: " + tokens.toString());
		assertNotEquals(NonTerminalSymbol.parseInput(tokens), Optional.empty());
	}

	@Test
	public void testEmptyParse() {
		List<Token> tokens = new ArrayList<>();
		System.out.println("Test 2: empty list");
		assertEquals(NonTerminalSymbol.parseInput(tokens), Optional.empty());
	}

	@Test
	public void testBadParse() {
		List<Token> tokens = new ArrayList<>();
		Variable v1 = Variable.build("x");
		Connector c1 = Connector.build(TerminalSymbol.PLUS);
		Variable v2 = Variable.build("y");
		Connector c2 = Connector.build(TerminalSymbol.DIVIDE);
		Variable v3 = Variable.build("z");

		tokens.add(v1);
		tokens.add(v2);
		tokens.add(c1);
		tokens.add(c2);
		tokens.add(v3);

		System.out.println("bad list: " + tokens);
		System.out.println("Test 3: bad list, should return empty optional");
		Optional<Node> test = NonTerminalSymbol.parseInput(tokens);
		System.out.println("testBadParse: " + test.toString());
		assertEquals(test, Optional.empty());
	}

}
