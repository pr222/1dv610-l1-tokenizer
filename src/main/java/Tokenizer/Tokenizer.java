package Tokenizer;

import java.util.ArrayList;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private final ArrayList<Token> tokenized;
    // private Token activeToken;
    private final GrammarRules grammar;
    private String leftToTokenize;
    private int currentPosition;

    public Tokenizer(GrammarRules grammar, String input) throws Exception {
        this.tokenized = new ArrayList<>();
        this.grammar = grammar;
        this.leftToTokenize = input;
        leftToTokenize = leftToTokenize.trim();
        currentPosition = 0;

        Token endToken = new Token("END", "");
        tokenized.add(endToken);

        if(!leftToTokenize.isEmpty()) {
            this.match();
        }
    }

    public void next() throws Exception {
        if(!tokenized.get(currentPosition).getType().equals("END")){
            if(currentPosition == (tokenized.size() - 2)) {
                if(!leftToTokenize.isEmpty()) {
                    this.match();
                }
            }

            currentPosition++;
        }
    }

    public void previous() {
        if(currentPosition > 0) {
            currentPosition--;
        }
    }

    public Token getActiveToken() {
        return tokenized.get(this.currentPosition);
    }

    private void match() throws Exception {
        Token bestMatch = new Token();
        int endPoint = 0;

        for (TokenRule rule : this.grammar.getRules()) {
            Pattern pattern = Pattern.compile(rule.getRegex());

            Matcher match = pattern.matcher(leftToTokenize);

            boolean found = match.find();

            if(found) {
                MatchResult res = match.toMatchResult();
                endPoint = res.end();

                String value = leftToTokenize.substring(res.start(), res.end());

                Token token = new Token(rule.getName(), value);

                if(token.getValue().length() > bestMatch.getValue().length()) {
                    bestMatch = token;
                }
            }
        }
        if(endPoint > 0) {
            leftToTokenize = leftToTokenize.substring(endPoint);
        }

        leftToTokenize = leftToTokenize.trim();

        if(bestMatch.getValue().length() > 0) {
            tokenized.add(tokenized.size()-1, bestMatch);
        } else {
            throw new Exception("Could not make a valid match!");
        }
    }
}
