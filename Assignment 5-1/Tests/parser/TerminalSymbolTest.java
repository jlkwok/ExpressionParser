package edu.cwru.jfh86_jlk187.parser;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TerminalSymbolTest {

		@Test
		public void testParse()
		{
			List<Token> tokens1 = new ArrayList<>();
			Variable v1 = Variable.build("a");
			Variable v2 = Variable.build("b");
			tokens1.add(v1);
			tokens1.add(v2);
			Node leaf1 = LeafNode.build(v1);
			List<Token> tokens2 = new ArrayList<>(tokens1.subList(1, tokens1.size()));

			ParseState test = ParseState.build(leaf1, tokens2);

			TerminalSymbol testSymb1 = TerminalSymbol.VARIABLE;
			assertEquals(testSymb1.parse(tokens1).getNode().toString(), test.getNode().toString());
			assertEquals(testSymb1.parse(tokens1).getRemainder(), test.getRemainder());
			assertEquals(testSymb1.parse(tokens1).getSuccess(), test.getSuccess());

			TerminalSymbol testSymb2 = TerminalSymbol.PLUS;
			assertEquals(testSymb2.parse(tokens1), ParseState.FAILURE);
		}
}
