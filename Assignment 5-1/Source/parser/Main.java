package edu.cwru.jfh86_jlk187.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to the mathematical expression parser...");
        System.out.println("Please enter an expression in the format a*b/c , and this will output a tree representation:");

        Scanner scanner = new Scanner(System.in);
        char[] expressionChars = scanner.next().toCharArray();

        List<Token> inputTokens = new ArrayList<>();

        for (int i = 0; i < expressionChars.length; i++)
        {
            Token inputToken;
            switch(expressionChars[i])
            {
                case '-':
                    inputToken = Connector.build(TerminalSymbol.MINUS);
                case '+':
                    inputToken = Connector.build(TerminalSymbol.PLUS);
                case '*':
                    inputToken = Connector.build(TerminalSymbol.TIMES);
                case '/':
                    inputToken = Connector.build(TerminalSymbol.DIVIDE);
                case '(':
                    inputToken = Connector.build(TerminalSymbol.OPEN);
                case ')':
                    inputToken = Connector.build(TerminalSymbol.CLOSE);
                default:
                    inputToken = Variable.build("" + expressionChars[i]);
            }
            inputTokens.add(inputToken);
        }

        System.out.println(inputTokens.toString());
    }
}
