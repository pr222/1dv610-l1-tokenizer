package Tokenizer;

import java.util.ArrayList;

/**
 * Grammar class managing a collection of rules.
 */
public class GrammarRules {
    ArrayList<TokenRule> rules;

    public GrammarRules() {
        this.rules = new ArrayList<>();
    }

    public void addRule(TokenRule rule) {
        rules.add(rule);
    }

    public ArrayList<TokenRule> getRules() {
        return rules;
    }
}
