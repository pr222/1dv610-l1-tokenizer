package Tokenizer;

import java.util.ArrayList;

public class Tokenizer {
    private final ArrayList<Token> tokenized;
    private final TokenGrammar grammar;
    private String leftToTokenize;
    private int activeTokenPosition;

    public Tokenizer(TokenGrammar grammar, String input) throws Exception {
        this.tokenized = new ArrayList<>();
        this.grammar = grammar;
        this.leftToTokenize = input.trim();

        activeTokenPosition = 0;
        tokenized.add(new Token("END", ""));

        this.tokenize();
    }

    public Token getActiveToken() {
        return tokenized.get(this.activeTokenPosition);
    }

    public void next() throws Exception {
        if(isNotOnEND()){
            if(isNotRightBeforeEND()) {
                this.tokenize();
            }
            activeTokenPosition++;
        }
    }

    private boolean isNotOnEND() {
        return !tokenized.get(activeTokenPosition).getType().equals("END");
    }

    private boolean isNotRightBeforeEND() {
        return activeTokenPosition == (tokenized.size() - 2);
    }

    private boolean hasLeftToTokenize() {
        return !leftToTokenize.isEmpty();
    }

    public void previous() {
        if(isNotAtStartingPoint()) {
            activeTokenPosition--;
        }
    }

    private boolean isNotAtStartingPoint() {
        return activeTokenPosition > 0;
    }

    private void tokenize() throws Exception {
        if(hasLeftToTokenize()) {
            Token matchedToken = match();

            insertBeforeEND(matchedToken);
            cleanUpLeftToTokenize(matchedToken);
        }
    }

    private Token match() throws Exception {
        Token bestMatch = new Token();

        for (TokenMatchRule rule : this.grammar.getRules()) {
            Token match = rule.matchToken(leftToTokenize);

            boolean isBetter = match.isLongerMatchThan(bestMatch);

            if (isBetter) {
                bestMatch = match;
            }
        }

        if (bestMatch.isValidMatch()) {
            return bestMatch;
        } else {
            throw new Exception("Could not make a valid matched token!");
        }
    }

    private void insertBeforeEND(Token token) {
        tokenized.add(tokenized.size()-1, token);
    }

    private void cleanUpLeftToTokenize(Token toCleanOut) {
        leftToTokenize = leftToTokenize.substring(toCleanOut.getValue().length());
        leftToTokenize = leftToTokenize.trim();
    }
}
