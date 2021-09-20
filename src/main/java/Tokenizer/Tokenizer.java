package Tokenizer;

// TODO: Make sure matching is maximal munched
// TODO: Throw exception when a token could not be matched (no valid rule)

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private Token activeToken;
    private GrammarRules grammar;
    private String leftToTokenize;
    private final Token END;
    private ArrayList<Token> tokenized;
    private int currentPosition;

    public Tokenizer(GrammarRules grammar, String input) {
        this.grammar = grammar;
        this.leftToTokenize = input;
        this.END = new Token("END", "");
        this.tokenized = new ArrayList<Token>();
        // TODO: Match first token and make it the active token.
        tokenized.add(END);

        this.match();
    }

    /**
     * Move one step forward but do not exceed number of available tokens.
     */
    public void next() {
        if(!tokenized.get(currentPosition).equals(this.END)){
            if(leftToTokenize.length() > 0) {
                match();
            }

            currentPosition++;
        }
    }

    /**
     * Move 1 step back and do nothing if already on first index (0).
     */
    public void previous() {
        if(currentPosition > 0) {
            currentPosition--;
        }
    }

    public Token getActiveToken() {
        return tokenized.get(this.currentPosition);
    }

    private void match() {
        ArrayList<Token> matches = new ArrayList<Token>();

        for (TokenRule rule : this.grammar.getRules()) {
            Pattern pattern = Pattern.compile(rule.getRegex());

            Matcher matcher = pattern.matcher(leftToTokenize);

            boolean matched = matcher.matches();

            if(matched) {
                // TODO: change, split in the right value
                Token t = new Token(rule.getName(), "MATCHED!");
                matches.add(t);
            }
        }
        // TODO: remove
        leftToTokenize = "";

        tokenized.add(0, matches.get(0));
    }
}
