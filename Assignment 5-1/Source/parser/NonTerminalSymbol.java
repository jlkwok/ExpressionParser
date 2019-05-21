package edu.cwru.jfh86_jlk187.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

enum NonTerminalSymbol implements Symbol
{
	// enum types for the different tokens in an expression
	EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;

	private static final Map<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>> prodMap = new HashMap<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>>();
	static
	{
		// EXPRESSION
		Map<TerminalSymbol, SymbolSequence> expr = new HashMap<>();
		termSymToSymSeq(expr, Arrays.asList(TerminalSymbol.MINUS, TerminalSymbol.OPEN, TerminalSymbol.VARIABLE), SymbolSequence.build(TERM, EXPRESSION_TAIL));
		prodMap.put(EXPRESSION, expr);

		// EXPRESSION_TAIL
		Map<TerminalSymbol, SymbolSequence> exprTail = new HashMap<>();
		termSymToSymSeq(exprTail, Arrays.asList(TerminalSymbol.PLUS), SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL));
		termSymToSymSeq(exprTail, Arrays.asList(TerminalSymbol.MINUS), SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL));
		termSymToSymSeq(exprTail, Arrays.asList(null, TerminalSymbol.CLOSE), SymbolSequence.EPSILON);
		prodMap.put(EXPRESSION_TAIL, exprTail);

		// TERM
		Map<TerminalSymbol, SymbolSequence> term = new HashMap<>();
		termSymToSymSeq(term, Arrays.asList(TerminalSymbol.MINUS, TerminalSymbol.OPEN, TerminalSymbol.VARIABLE), SymbolSequence.build(UNARY, TERM_TAIL));
		prodMap.put(TERM, term);

		// TERM-TAIL
		Map<TerminalSymbol, SymbolSequence> termTail = new HashMap<>();
		termSymToSymSeq(termTail, Arrays.asList(TerminalSymbol.TIMES), SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL));
		termSymToSymSeq(termTail, Arrays.asList(TerminalSymbol.DIVIDE), SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL));
		termSymToSymSeq(termTail, Arrays.asList(TerminalSymbol.PLUS, TerminalSymbol.MINUS, TerminalSymbol.CLOSE, null), SymbolSequence.EPSILON);
		prodMap.put(TERM_TAIL, termTail);

		// UNARY
		Map<TerminalSymbol, SymbolSequence> unary = new HashMap<>();
		termSymToSymSeq(unary, Arrays.asList(TerminalSymbol.MINUS), SymbolSequence.build(TerminalSymbol.MINUS, FACTOR));
		termSymToSymSeq(unary, Arrays.asList(TerminalSymbol.OPEN, TerminalSymbol.VARIABLE), SymbolSequence.build(FACTOR));
		prodMap.put(UNARY, unary);

		// FACTOR
		Map<TerminalSymbol, SymbolSequence> factor = new HashMap<>();
		termSymToSymSeq(factor, Arrays.asList(TerminalSymbol.OPEN), SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE));
		termSymToSymSeq(factor, Arrays.asList(TerminalSymbol.VARIABLE), SymbolSequence.build(TerminalSymbol.VARIABLE));
		prodMap.put(FACTOR, factor);
	}

	public static void termSymToSymSeq(Map<TerminalSymbol, SymbolSequence> map, List<TerminalSymbol> termSyms, SymbolSequence symSeq) {
		for (TerminalSymbol ts : termSyms) 
			map.put(ts, symSeq);
	}
	
	public ParseState parse(List<Token> input)
	{
		// If token list is not empty, return ParseState:
		if (!input.isEmpty()) 
			return prodMap.get(this).get(Objects.requireNonNull(input,"Input must not be null").get(0).getType()).match(input);
		// Else return failure:
		return ParseState.FAILURE;

	}

	static final Optional<Node> parseInput(List<Token> input)
	{
		ParseState state = EXPRESSION.parse(Objects.requireNonNull(input, "Input must not be null"));
		System.out.println("state: " + state);
		if (state.getSuccess() && state.hasNoRemainder())
		{
			return Optional.of(state.getNode());
		}
		else
		{
			return Optional.empty();
		}
	}
}
