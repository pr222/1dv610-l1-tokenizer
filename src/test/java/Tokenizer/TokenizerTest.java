package Tokenizer;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

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

    @Test void TC1() throws Exception {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a");
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("WORD", active.getType(), "Active token type should be 'WORD'");
        Assertions.assertEquals("a", active.getValue(), "Active token value should be 'a'");
    }

    @Test void TC2() throws Exception {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a aa");
        tokenizer.next();
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("WORD", active.getType(), "Active token type should be 'WORD'");
        Assertions.assertEquals("aa", active.getValue(), "Active token value should be 'aa'");
    }

    @Test void TC3() throws Exception {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a.b");
        tokenizer.next();
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("DOT", active.getType(), "Active token type should be 'DOT'");
        Assertions.assertEquals(".", active.getValue(), "Active token value should be '.'");
    }

    @Test void TC4() throws Exception {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a.b");
        tokenizer.next();
        tokenizer.next();
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("WORD", active.getType(), "Active token type should be 'WORD'");
        Assertions.assertEquals("b", active.getValue(), "Active token value should be 'b'");
    }

    @Test void TC5() throws Exception {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "aa. b");
        tokenizer.next();
        tokenizer.next();
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("WORD", active.getType(), "Active token type should be 'WORD'");
        Assertions.assertEquals("b", active.getValue(), "Active token value should be 'b'");
    }

    @Test void TC6() throws Exception {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a .b");
        tokenizer.next();
        tokenizer.next();
        tokenizer.previous();
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("DOT", active.getType(), "Active token type should be 'DOT'");
        Assertions.assertEquals(".", active.getValue(), "Active token value should be '.'");
    }

    @Test void TC7() throws Exception {
        //
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "");
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("END", active.getType(), "Active token type should be 'END'");
        Assertions.assertEquals("", active.getValue(), "Active token value should be ''");
    }

    @Test void TC8() throws Exception {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, " ");
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("END", active.getType(), "Active token type should be 'END'");
        Assertions.assertEquals("", active.getValue(), "Active token value should be ''");
    }

    @Test void TC9() throws Exception {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a");
        tokenizer.next();
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("END", active.getType(), "Active token type should be 'END'");
        Assertions.assertEquals("", active.getValue(), "Active token value should be ''");
    }

    /**
     * Going back further from the start should still stay on the starting token.
     */
    @Test  void TC10() throws Exception {
        Tokenizer tokenizer = new Tokenizer(wordAndDot, "a");
        tokenizer.previous();
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("WORD", active.getType(), "Active token type should be 'WORD'");
        Assertions.assertEquals("a", active.getValue(), "Active token value should be 'a'");
    }

    @Test void TC11() throws Exception {
        Assertions.assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Tokenizer t = new Tokenizer(wordAndDot, "!");
            }
        });
    }

    @Test void TC12() throws Exception {
        Tokenizer tokenizer = new Tokenizer(arithmetic, "3");
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("NUMBER", active.getType(), "Active token type should be 'NUMBER'");
        Assertions.assertEquals("3", active.getValue(), "Active token value should be '3'");
    }

    @Test void TC13() throws Exception {
        Tokenizer tokenizer = new Tokenizer(arithmetic, "3.14");
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("NUMBER", active.getType(), "Active token type should be 'NUMBER'");
        Assertions.assertEquals("3.14", active.getValue(), "Active token value should be '3.14'");
    }

    @Test void TC14() throws Exception {
        Tokenizer tokenizer = new Tokenizer(arithmetic, "3 + 54 * 4");
        tokenizer.next();
        tokenizer.next();
        tokenizer.next();
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("MUL", active.getType(), "Active token type should be 'MUL'");
        Assertions.assertEquals("*", active.getValue(), "Active token value should be '*'");
    }

    @Test void TC15() throws Exception {
        Tokenizer tokenizer = new Tokenizer(arithmetic, "3+5 # 4");
        tokenizer.next();
        tokenizer.next();

        // Calling next a third time in assertion, throwing exception because of # in input.
        Assertions.assertThrows(Exception.class, tokenizer::next);
    }

    @Test void TC16() throws Exception {
        Tokenizer tokenizer = new Tokenizer(arithmetic, "3.0+54.1     + 4.2");
        tokenizer.next();
        tokenizer.previous();
        tokenizer.next();
        tokenizer.next();
        tokenizer.next();
        Token active = tokenizer.getActiveToken();

        Assertions.assertEquals("ADD", active.getType(), "Active token type should be 'ADD'");
        Assertions.assertEquals("+", active.getValue(), "Active token value should be '+'");
    }

     // TODO: TC for going next when aciteToken is END, stays back on end-token and activeToken stays the same
}
