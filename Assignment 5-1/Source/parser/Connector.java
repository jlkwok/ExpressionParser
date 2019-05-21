package edu.cwru.jfh86_jlk187.parser;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

//Connectors: symbols in list representation, such  as  +,  -,  *,  /,  (,  and  )
public final class Connector extends AbstractToken{

	// type of connector
	private TerminalSymbol type;
	private static Cache<TerminalSymbol, Connector> cache = new Cache<TerminalSymbol, Connector>();
	// okay to leave in Connector class
	// use static block
    static Map<TerminalSymbol, String> buildTypes = new EnumMap<>(TerminalSymbol.class);
    static {
        buildTypes.put(TerminalSymbol.PLUS,"+");
        buildTypes.put(TerminalSymbol.MINUS,"-");
        buildTypes.put(TerminalSymbol.TIMES,"*");
        buildTypes.put(TerminalSymbol.DIVIDE,"/");
        buildTypes.put(TerminalSymbol.OPEN,"(");
        buildTypes.put(TerminalSymbol.CLOSE,")");
        buildTypes = Collections.unmodifiableMap(buildTypes);
    }
	
	// sets type of connector
	private Connector(TerminalSymbol type) {
		this.type = type;
	}
	
	// obtains type of this.connector
	public TerminalSymbol getType() {
		return this.type;
	}
	
	// if connector with given type is in cache -> returns corresponding connector
	// null type -> NullPointerException
	// can only pass in types: PLUS, MINUS, TIMES, DIVIDE, CLOSE, OPEN, if not -> IllegalArgumentExcpetion
	public static final Connector build(TerminalSymbol type)
	{
		if (buildTypes.containsKey(type))
			return cache.get(type, Connector::new);
		else
			throw new IllegalArgumentException("Type must be one of the following: plus, minus, times, divide, close, or open");
	}

	// returns the character of what the type is for the given connector
	public String toString()
	{
		if (buildTypes.containsKey(this.type))
			return buildTypes.get(this.type);
		else 
			return "Connector not valid";
	}

	public boolean isOperator() {
		return (TerminalSymbol.operators.contains(this.getType()));
	}
}
