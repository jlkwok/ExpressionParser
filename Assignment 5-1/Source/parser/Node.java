package edu.cwru.jfh86_jlk187.parser;

import java.util.*;

public interface Node
{
    List<Token> toList();

    List<Node> getChildren();

    boolean isFruitful();
    
    boolean isOperator();
    
    boolean isStartedByOperator();
    
    Optional<Node> firstChild();
    
    boolean isSingleLeafParent();
}
