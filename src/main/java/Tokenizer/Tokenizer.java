package Tokenizer;

// TODO: Make sure matching is maximal munched
// TODO: Throw exception when a token could not be matched (no valid rule)

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private ArrayList<Token> tokenized;
    private Token activeToken;
    private final GrammarRules grammar;
    private String leftToTokenize;
    private int currentPosition;

    public Tokenizer(GrammarRules grammar, String input) {
        this.tokenized = new ArrayList<Token>();
        this.grammar = grammar;
        this.leftToTokenize = input;
        leftToTokenize.trim();

        currentPosition = 0;

        Token endToken = new Token("END", "");
        tokenized.add(endToken);

        this.match();
    }

    /**
     * Move one step forward but do not exceed number of available tokens.
     */
    public void next() {
        if(tokenized.get(currentPosition).getType() != "END"){
            if(currentPosition == (tokenized.size() - 1)) {
                if(!leftToTokenize.isEmpty()) {
                    match();
                }
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
        Token bestMatch = new Token();

        for (TokenRule rule : this.grammar.getRules()) {
            Pattern pattern = Pattern.compile(rule.getRegex());

            Matcher match = pattern.matcher(leftToTokenize);

            boolean matched = match.matches();

            if(matched) {
                int start = match.start();
                int end = match.end();

                String value = leftToTokenize.substring(start, end);
                leftToTokenize = leftToTokenize.substring(end);
                leftToTokenize.trim();

                Token matchedToken = new Token(rule.getName(), value);

                if(bestMatch.getValue().length() < matchedToken.getValue().length()) {
                    bestMatch = matchedToken;
                }
            }
        }

        tokenized.add(0, bestMatch);
    }
}
