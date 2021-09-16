package Tokenizer;

public class Token {
    private TokenRule matchRule;
    private String value;

    public Token(TokenRule matchRule, String value) {
        this.matchRule = matchRule;
        this.value = value;
    }

    public void setMatchRule(TokenRule matchRule) {
        this.matchRule = matchRule;
    }

    public String getValue() {
        return value;
    }
}
