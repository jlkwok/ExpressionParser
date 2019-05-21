package edu.cwru.jfh86_jlk187.parser;

// Class that implements the matches method of Token
public abstract class AbstractToken implements Token
{
	// Returns whether the argument is equal to getType()
	@Override
	public final boolean matches(TerminalSymbol type)
	{
		return (type == this.getType());
	}
}
