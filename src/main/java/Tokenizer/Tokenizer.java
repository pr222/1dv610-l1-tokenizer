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

    /**
     * Move one step forward but do not exceed number of available tokens.
     */
    public void next() throws Exception {
        if(!tokenized.get(currentPosition).getType().equals("END")){
            // System.out.println("not at end");
            if(currentPosition == (tokenized.size() - 2)) {
                // System.out.println("at the border");
                if(!leftToTokenize.isEmpty()) {
                    // System.out.println("not empty, go match!");
                    this.match();
                    // System.out.println("Matched???");
                }
            }

            currentPosition++;
        }
        // System.out.println("NExt postition: " + currentPosition);
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

    private void match() throws Exception {
        Token bestMatch = new Token();
        int endPoint = 0;
        // System.out.println("LEFT TO TOKENIZE AT START OF MATCH " + leftToTokenize);

        for (TokenRule rule : this.grammar.getRules()) {
            // System.out.println("RULE: " + rule.name);
            Pattern pattern = Pattern.compile(rule.getRegex());

            Matcher match = pattern.matcher(leftToTokenize);
            // System.out.println(match);

            boolean found = match.find();
            // System.out.println("FOUND? - " + found);
            // System.out.println(match);

            if(found) {
                MatchResult res = match.toMatchResult();
                // System.out.println("Start index: " + res.start());
                // System.out.println("Plus index: " + res.end());
                endPoint = res.end();
                // System.out.println(match);

                String value = leftToTokenize.substring(res.start(), res.end());
                // System.out.println("value " + value);
                // System.out.println(leftToTokenize);

                Token token = new Token(rule.getName(), value);

                if(token.getValue().length() > bestMatch.getValue().length()) {
                    bestMatch = token;
                    // System.out.println("UPDATED BEST MATCH: " + bestMatch.getValue() + " " + bestMatch.getType());
                }
            }
        }
        if(endPoint > 0) {
            // System.out.println("left before SUBBING: " + leftToTokenize);
            leftToTokenize = leftToTokenize.substring(endPoint);
        }

        // System.out.println("left after SUBBING: " + leftToTokenize);
        leftToTokenize = leftToTokenize.trim();
        // System.out.println("left after TRIMMING: " + leftToTokenize);

        // System.out.println("Last best match: " + bestMatch.getValue() + " " + bestMatch.getType());
        if(bestMatch.getValue().length() > 0) {
            tokenized.add(tokenized.size()-1, bestMatch);
        } else {
            throw new Exception("Could not make a valid match!");
        }
    }
}
