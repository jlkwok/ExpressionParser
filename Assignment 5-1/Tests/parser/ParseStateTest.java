package edu.cwru.jfh86_jlk187.parser;

import static org.junit.Assert.*; 
import java.util.*;
import org.junit.Test;

public class ParseStateTest {
	
	List<Token> tokens = new ArrayList<>();
	LeafNode leaf1;
	ParseState test;
	
	@Test
	public void testGetSuccess() {
	    Variable v1 = Variable.build("a");
	    Variable v2 = Variable.build("b");
	    tokens.add(v1);
	    tokens.add(v2);

	    leaf1 = LeafNode.build(v1);
	    test = ParseState.build(leaf1, tokens);
		assertTrue(test.getSuccess());
	}
	
	@Test
	public void testGetNode() {
	    Variable v1 = Variable.build("a");
	    Variable v2 = Variable.build("b");
	    tokens.add(v1);
	    tokens.add(v2);

	    leaf1 = LeafNode.build(v1);
	    test = ParseState.build(leaf1, tokens);
	    assertEquals(test.getNode(), leaf1);
	}
	
	@Test
	public void testGetRemainder() {
	    Variable v1 = Variable.build("a");
	    Variable v2 = Variable.build("b");
	    tokens.add(v1);
	    tokens.add(v2);

	    leaf1 = LeafNode.build(v1);
	    test = ParseState.build(leaf1, tokens);
	    assertEquals(test.getRemainder(), tokens);
	}
	
	@Test
	public void testHasNoRemainder() {
	    Variable v1 = Variable.build("a");
	    Variable v2 = Variable.build("b");
	    tokens.add(v1);
	    tokens.add(v2);

	    leaf1 = LeafNode.build(v1);
	    test = ParseState.build(leaf1, tokens);
		assertTrue(!test.hasNoRemainder());
	}

	@Test
	public void build() {
	}
}
