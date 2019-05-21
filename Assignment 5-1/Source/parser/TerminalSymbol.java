package edu.cwru.jfh86_jlk187.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum TerminalSymbol implements Symbol {
	// enum types for the different tokens in an expression
	VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;
	
    static List<TerminalSymbol> operators = new ArrayList<>();
    static
	{
    	operators.add(TerminalSymbol.PLUS);
    	operators.add(TerminalSymbol.MINUS);
    	operators.add(TerminalSymbol.TIMES);
    	operators.add(TerminalSymbol.DIVIDE);
    	operators = Collections.unmodifiableList(operators);
    }

	public ParseState parse(List<Token> input)
	{
		ParseState result = ParseState.FAILURE;
		/*
		 * If the first input token matches the terminal  type,  then the  parsing  is  successful,  
		 * the  node  is  a  leaf  containing the first matched token, and the remainder is the rest of the  
		 * input  list.  In  all  other  cases,  the  parsing  process  returns  the  failure
		 */

		if (!input.isEmpty() && input.get(0).matches(this))
		{
			result = ParseState.build(LeafNode.build(input.get(0)), input.subList(1, input.size()));
		}

		return result;
	}
}
