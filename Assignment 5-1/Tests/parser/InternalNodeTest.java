package edu.cwru.jfh86_jlk187.parser;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class InternalNodeTest {
	
	static Connector plus;
	static LeafNode plusNode;
	static List<Node> list;
	static InternalNode in;
	static List<Token> tokenList;
	static Variable v1;
	static Variable v2;
	static LeafNode leaf1;
	static LeafNode leaf2;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
    public void setUp() {
        plus = Connector.build(TerminalSymbol.PLUS);
        plusNode = LeafNode.build(plus);
        list = new ArrayList<>();
        list.add(plusNode);
        in = InternalNode.build(list);
        
    	tokenList = new ArrayList<>();
    	v1 = Variable.build("v1");
    	v2 = Variable.build("v2");
    	leaf1 = LeafNode.build(v1);
    	leaf2 = LeafNode.build(v2);
    }

    @Test
    public void testBuild() {
		assertNotNull(InternalNode.build(list));
    }
    
    @Test 
    public void testNullBuild() {
    	thrown.expect(NullPointerException.class);
    	InternalNode.build(null);
    }

    @Test
    public void testGetChildren() {
    	assertEquals(list, in.getChildren());
    }
    
    @Test
	public void testToList() {
		tokenList.add(v1);
		tokenList.add(v2);
		
		List<Node> children = new ArrayList<>();
		children.add(leaf1);
		children.add(leaf2);
		
		InternalNode node1 = InternalNode.build(children);
		assertEquals(node1.toList(), tokenList);
		
		children.add(node1);
		InternalNode node2 = InternalNode.build(children);
		
		tokenList.add(v1);
		tokenList.add(v2);
		
		assertEquals(node2.toList(), tokenList);
		
		assertNotNull(in.toList());
	}
	
	@Test
	public void testToString() {
    	Variable var1 = Variable.build("a");
		Variable var2 = Variable.build("b");
		
		LeafNode leaf1 = LeafNode.build(var1);
		LeafNode leaf2 = LeafNode.build(var2);
		
		List<Node> children = new ArrayList<>();
		children.add(leaf1);
		children.add(leaf2);	
		
		InternalNode node1 = InternalNode.build(children);
		assertEquals("[a,b]", node1.toString());
		
		children.remove(1);
		children.add(node1);
		
		InternalNode node2 = InternalNode.build(children);		
		assertEquals(node2.toString(), "[a,[a,b]]");
		
	}
}