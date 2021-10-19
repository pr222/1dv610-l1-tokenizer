package Tokenizer;

import java.util.ArrayList;

/**
 * Grammar class managing a collection of rules.
 */
public class TokenGrammar {
    ArrayList<TokenMatchRule> rules;

    public TokenGrammar() {
        this.rules = new ArrayList<>();
    }

    public void addRule(TokenMatchRule rule) {
        rules.add(rule);
    }

    public ArrayList<TokenMatchRule> getRules() {
        return rules;
    }
}
