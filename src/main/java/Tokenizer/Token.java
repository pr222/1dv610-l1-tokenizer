package Tokenizer;

/**
 * A Token with a matched value and the token that was used for the match.
 */
public class Token {
    private String type;
    private String value;

    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
