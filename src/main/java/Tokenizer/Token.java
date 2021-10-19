package Tokenizer;

public class Token {
    private final String type;
    private final String value;

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

    public String getType() {
        return type;
    }

    public boolean isLongerMatchThan(Token anotherToken) {
        return value.length() > anotherToken.getValue().length();
    }

    public boolean isValidMatch() {
        return value.length() > 0;
    }
}
