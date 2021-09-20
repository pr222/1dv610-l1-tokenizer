package Tokenizer;

/**
 * A Token with a matched value and the token that was used for the match.
 */
public class Token {
    private TokenRule rule;
    private String value;

    public Token(TokenRule rule, String value) {
        this.rule = rule;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return rule.getName();
    }

    public TokenRule getTokenRule() {
        return rule;
    }
}
