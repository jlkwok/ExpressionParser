package edu.cwru.jfh86_jlk187.parser;

import java.util.*;

public final class LeafNode implements Node
{
    private final Token nodeToken;
    static List<TerminalSymbol> operators = new ArrayList<>();
    static {
        operators.add(TerminalSymbol.PLUS);
        operators.add(TerminalSymbol.MINUS);
        operators.add(TerminalSymbol.TIMES);
        operators.add(TerminalSymbol.DIVIDE);
        operators = Collections.unmodifiableList(operators);
    }

    private LeafNode(Token newToken)
    {
        nodeToken = newToken;
    }

    public static LeafNode build(Token newToken)
    {
        return new LeafNode(Objects.requireNonNull(newToken, "Token for leaf node was not specified, operation canceled"));
    }

    public Token getNodeToken()
    {
        return nodeToken;
    }

    public final List<Token> toList()
    {
        return new ArrayList<>(Arrays.asList(nodeToken));
    }

    public final List<Node> getChildren(){ return null; }

    public final boolean isFruitful(){ return true; }

    public String toString()
    {
        return nodeToken.toString();
    }

	public boolean isOperator() { return operators.contains(this.getNodeToken().getType());}

	public boolean isStartedByOperator() {
		return false;
	}

    public Optional<Node> firstChild() { return Optional.empty(); }

    public boolean isSingleLeafParent() { return false; }
}
