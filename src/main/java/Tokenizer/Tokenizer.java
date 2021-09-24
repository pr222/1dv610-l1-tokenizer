package Tokenizer;

import java.util.ArrayList;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private final ArrayList<Token> tokenized;
    private final GrammarRules grammar;
    private String leftToTokenize;
    private int activeTokenPosition;

    public Tokenizer(GrammarRules grammar, String input) throws Exception {
        this.tokenized = new ArrayList<>();
        this.grammar = grammar;
        this.leftToTokenize = input.trim();

        activeTokenPosition = 0;
        tokenized.add(new Token("END", ""));

        if(hasLeftToTokenize()) {
            this.tokenize();
        }
    }

    public void next() throws Exception {
        if(isNotOnEND()){
            if(isNotRightBeforeEND()) {
                if(hasLeftToTokenize()) {
                    this.tokenize();
                }
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
        if(activeTokenPosition > 0) {
            activeTokenPosition--;
        }
    }

    public Token getActiveToken() {
        return tokenized.get(this.activeTokenPosition);
    }

    private void tokenize() throws Exception {
        Token bestMatch = new Token();

        for (TokenRule rule : this.grammar.getRules()) {
            Token match = matchToken(rule);

            bestMatch = maxMunched(match, bestMatch);
        }

        if (isValidMatch(bestMatch)) {
            insertBeforeEND(bestMatch);
            cleanUpLeftToTokenize(bestMatch);
        } else {
            throw new Exception("Could not make a valid matched token!");
        }
    }

    private Token matchToken(TokenRule rule) {
        String value = "";

        Pattern pattern = Pattern.compile(rule.getRegex());
        Matcher matcher = pattern.matcher(leftToTokenize);

        boolean found = matcher.find();

        if(found) {
            MatchResult result = matcher.toMatchResult();

            value = leftToTokenize.substring(result.start(), result.end());
        }

        return new Token(rule.getName(), value);
    }

    private Token maxMunched(Token latestMatch, Token bestMatchSoFar) {
        Token maxed;

        if (latestMatch.getValue().length() > bestMatchSoFar.getValue().length()) {
            maxed = latestMatch;
        } else {
            maxed = bestMatchSoFar;
        }

        return maxed;
    }

    private boolean isValidMatch(Token matchedToken) {
        return matchedToken.getValue().length() > 0;
    }

    private void insertBeforeEND(Token token) {
        tokenized.add(tokenized.size()-1, token);
    }

    private void cleanUpLeftToTokenize(Token toCleanOut) {
        leftToTokenize = leftToTokenize.substring(toCleanOut.getValue().length());
        leftToTokenize = leftToTokenize.trim();
    }
}
