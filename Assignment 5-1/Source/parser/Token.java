package edu.cwru.jfh86_jlk187.parser;

// Interface representing tokens (expression elements):
public interface Token
{	
	// Gets token type.
	TerminalSymbol getType();
	
	// Evaluates token type against input type.
	boolean matches(TerminalSymbol type);
	
	//
	boolean isOperator();
}
