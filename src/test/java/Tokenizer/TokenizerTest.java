package Tokenizer;

import org.junit.jupiter.api.*;

public class TokenizerTest {
    GrammarRules wordAndDot;
    GrammarRules arithmetic;

    @BeforeAll
    public void setupGrammars() {
        // Word and dot grammar
        wordAndDot = new GrammarRules();

        TokenRule word = new TokenRule("WORD", "^[\\w|åäöÅÄÖ]+");
        wordAndDot.addRule(word);

        TokenRule dot = new TokenRule("DOT", "^\\.");
        wordAndDot.addRule(dot);

        // Arithmetic grammar
        arithmetic = new GrammarRules();

        TokenRule number = new TokenRule("NUMBER", "^[0-9]+(\\.([0-9])+)?");
        arithmetic.addRule(number);

        TokenRule add = new TokenRule("ADD", "^[+]");
        arithmetic.addRule(add);

        TokenRule mul = new TokenRule("MUL", "^[*]");
        arithmetic.addRule(mul);
    }

    @Test void TC1() {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a");
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("WORD", active.getType(), "Active token type should be 'WORD'");
        Assertions.assertEquals("a", active.getValue(), "Active token value should be 'a'");
    }

    @Test void TC2() {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a aa");
        // TODO: >
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("WORD", active.getType(), "Active token type should be 'WORD'");
        Assertions.assertEquals("aa", active.getValue(), "Active token value should be 'aa'");
    }

    @Test void TC3() {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a.b");
        // TODO: >
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("DOT", active.getType(), "Active token type should be 'DOT'");
        Assertions.assertEquals(".", active.getValue(), "Active token value should be '.'");
    }
}
