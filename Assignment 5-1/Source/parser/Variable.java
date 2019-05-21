package edu.cwru.jfh86_jlk187.parser;

import java.util.Objects;

// Represents variables such as a, b, c in list representation in [a,  +, b, /, c]
// Extends AbstractToken -> implements Token interface
public final class Variable extends AbstractToken
{
	// Represents variable name - supposed to be non null:
	private final String representation;

	// Creates cache for Variables:
	private static Cache<String, Variable> cache = new Cache<String, Variable>();
	
	// Internal variable constructor -> sets the string representation for the variable:
	private Variable(String representation)
	{
		this.representation = representation;
	}

	// Public build method for a variable:
	// If in cache map -> returns variable with given representation.
	// If not in cache map -> will return new variable object with given representation.
	// If representation is null -> throw exception.
	public static final Variable build(String representation)
	{
		return cache.get(Objects.requireNonNull(representation, "Representation must be non-null"), Variable::new);
	}

	// Implements the getType method of Token interface
	public TerminalSymbol getType() { return TerminalSymbol.VARIABLE; }
	
	// Returns the representation (name) of the variable object.
	public final String getRepresentation() { return representation; }
	
	// Returns the variables representation as a string.
	public String toString() { return representation; }

	public boolean isOperator() {
		return false;
	}
}
