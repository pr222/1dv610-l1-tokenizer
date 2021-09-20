package Tokenizer;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    /**@Test void TC2() {
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

    @Test void TC4() {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a.b");
        // TODO: >>
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("WORD", active.getType(), "Active token type should be 'WORD'");
        Assertions.assertEquals("b", active.getValue(), "Active token value should be 'b'");
    }

    @Test void TC5() {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "aa. b");
        // TODO: >>>
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("WORD", active.getType(), "Active token type should be 'WORD'");
        Assertions.assertEquals("b", active.getValue(), "Active token value should be 'b'");
    }

    @Test void TC6() {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a .b");
        // TODO: >><
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("DOT", active.getType(), "Active token type should be 'DOT'");
        Assertions.assertEquals(".", active.getValue(), "Active token value should be '.'");
    }

    @Test void TC7() {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "");
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("END", active.getType(), "Active token type should be 'END'");
        // TODO: Handle END?
        // Assertions.assertEquals("", active.getValue(), "Active token value should be ''");
    }

    @Test void TC8() {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, " ");
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("END", active.getType(), "Active token type should be 'END'");
        // TODO: Handle END?
        // Assertions.assertEquals("", active.getValue(), "Active token value should be ''");
    }

    @Test void TC9() {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a");
        // TODO: >
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("END", active.getType(), "Active token type should be 'END'");
        // TODO: Handle END?
        // Assertions.assertEquals("", active.getValue(), "Active token value should be ''");
    }

    @Test void TC10() {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a");
        // TODO: <
        Token active = tokenizer.getActiveToken();

        // TODO: Handle END in start?
        Assertions.assertEquals("END", active.getType(), "Active token type should be 'END'");
        // Assertions.assertEquals("", active.getValue(), "Active token value should be ''");
    }

    @Test void TC11() {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "!");
        // TODO: TEST EXCEPTION
    }

    @Test void TC12() {
        Tokenizer tokenizer = new Tokenizer(arithmetic, "3");
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("NUMBER", active.getType(), "Active token type should be 'NUMBER'");
        Assertions.assertEquals("3", active.getValue(), "Active token value should be '3'");
    }

    @Test void TC13() {
        Tokenizer tokenizer = new Tokenizer(arithmetic, "3.14");
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("NUMBER", active.getType(), "Active token type should be 'NUMBER'");
        Assertions.assertEquals("3.14", active.getValue(), "Active token value should be '3.14'");
    }

    @Test void TC14() {
        Tokenizer tokenizer = new Tokenizer(arithmetic, "3 + 54 * 4");
        // TODO: >>>
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("MUL", active.getType(), "Active token type should be 'MUL'");
        Assertions.assertEquals("*", active.getValue(), "Active token value should be '*'");
    }

    @Test void TC15() {
        Tokenizer tokenizer = new Tokenizer(arithmetic, "3+5 # 4");
        // TODO: >>>
        Token active = tokenizer.getActiveToken();

        // TODO: TEST EXCEPTION
    }

    @Test void TC16() {
        Tokenizer tokenizer = new Tokenizer(arithmetic, "3.0+54.1     + 4.2");
        // TODO: ><>>>
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("ADD", active.getType(), "Active token type should be 'ADD'");
        Assertions.assertEquals("+", active.getValue(), "Active token value should be '+'");
    }

     // TODO: TC for going next stays back on end-token, activeToken stays the same

     // TODO: TC for going previous stays on first token, activeToken stays as the one on index 0
    */
}
