package edu.cwru.jfh86_jlk187.parser;

import java.util.List;
import java.util.Objects;

final class ParseState {
	
	// whether parsing process was successful
	private final boolean success;
	// result of parsing initial prefix
    private final Node node;
    // remainder after parsing initial prefix
    private final List<Token> remainder;
    
    final static ParseState FAILURE = new ParseState(false,null, null);

    private ParseState(boolean success, Node node, List<Token> remainder){
        this.success = success;
        this.node = node;
        this.remainder = remainder;
    }
    
    public boolean getSuccess() {
    	return this.success;
    }
    
    public Node getNode() {
    	return this.node;
    }
    
    public List<Token> getRemainder() { return this.remainder; }
    
    public final boolean hasNoRemainder() {
    	return remainder.isEmpty();
    }
    
    public static ParseState build(Node node, List<Token> remainder) {
    	return new ParseState(true, Objects.requireNonNull(node, "node cannot be null"), Objects.requireNonNull(remainder, "remainder cannot be null"));
    }
}
