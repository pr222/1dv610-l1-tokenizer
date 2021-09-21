package Tokenizer;

/**
 * A Token with a matched value and the token that was used for the match.
 */
public class Token {
    private String type;
    private String value;

    public Token() {
        this.type = "";
        this.value = "";
    }

    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
