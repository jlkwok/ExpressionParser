package edu.cwru.jfh86_jlk187.parser;

import java.util.List;

interface Symbol
{
	ParseState parse(List<Token> input);
}
