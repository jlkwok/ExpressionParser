package edu.cwru.jfh86_jlk187.parser;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;

public class LeafNodeTest
{

    Variable a;
    LeafNode sampleLeafNode;
    
    @Before
    public void setUp() {
    	a = Variable.build("a");
    	sampleLeafNode = LeafNode.build(a);
    }

    @Test
    public void getNodeToken()
    {
        assertEquals(a, LeafNode.build(a).getNodeToken());
        assertEquals(sampleLeafNode.getNodeToken(), LeafNode.build(a).getNodeToken());
    }

    @Test
    public void testToString()
    {
        assertEquals("a", sampleLeafNode.toString());
    }
}