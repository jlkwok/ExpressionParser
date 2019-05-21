package edu.cwru.jfh86_jlk187.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

final class SymbolSequence
{
    private final List<Symbol> production;

    static final SymbolSequence EPSILON = new SymbolSequence(new ArrayList<>());

    private SymbolSequence(List<Symbol> production)
    {
        this.production = production;
    }

    static SymbolSequence build(List<Symbol> inputProduction)
    {
        return new SymbolSequence(Objects.requireNonNull(inputProduction));
    }

    static SymbolSequence build(Symbol... symbols)
    {
        return new SymbolSequence(new ArrayList<>(Arrays.asList(Objects.requireNonNull(symbols))));
    }

    public String toString()
    {
        return production.toString();
    }

    ParseState match(List<Token> input)
    {
        List<Token> remainder = Objects.requireNonNull(input);

        InternalNode.Builder builder = new InternalNode.Builder();

        for (Symbol symbol : this.production)
        {
            ParseState result = symbol.parse(remainder);

            if (result.getSuccess())
            {
                builder.addChild(result.getNode());            
                remainder = result.getRemainder();
            }
            else
                return ParseState.FAILURE;
        }
        System.out.println("remainder: " + remainder);
        return ParseState.build(builder.build(), remainder);
    }
}
