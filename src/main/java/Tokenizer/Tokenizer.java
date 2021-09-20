package Tokenizer;

// TODO: Make sure matching is maximal munched
// TODO: Throw exception when a token could not be matched (no valid rule)

import java.util.ArrayList;

public class Tokenizer {
    private Token activeToken;
    // TODO: Choose fields
    private final GrammarRules grammar;
    private String leftToTokenize;
    private final Token END;
    private ArrayList<Token> tokenized;
    private int currentPosition;

    public Tokenizer(GrammarRules grammar, String input) {
        this.grammar = grammar;
        this.leftToTokenize = input;
        this.END = new Token(new TokenRule("END", ""), "");
        this.tokenized = new ArrayList<Token>();
        // TODO: Match first token and make it the active token.

    }

    public void next() {
        // TODO: Match next token (return it immediatelly or let user ask for active?)
    }

    public void prevoius() {
        // TODO: Move acitve token one step back of the matched tokens.
    }

    public Token getActiveToken() {
        return tokenized.get(this.currentPosition);
    }
}
