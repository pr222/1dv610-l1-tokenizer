package Tokenizer;

public class Token {
    private String type;
    private String value;

    public Token(String ruleName, String value) {
        this.type = ruleName;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
