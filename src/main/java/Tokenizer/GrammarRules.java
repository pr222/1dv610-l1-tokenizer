package Tokenizer;

import java.util.ArrayList;

public class GrammarRules {
    ArrayList<TokenRule> rules;

    public GrammarRules() {
        this.rules = new ArrayList<TokenRule>();
    }

    public void addRule(TokenRule rule) {
        rules.add(rule);

    }

    public ArrayList<TokenRule> getRules() {
        return rules;
    }
}
