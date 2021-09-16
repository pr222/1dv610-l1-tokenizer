package Tokenizer;

public class TokenRule {
    String type;
    String regex;

    public TokenRule(String type, String regex) {
        this.type = type;
        this.regex = regex;
    }

    public String getType() {
        return type;
    }

    public String getRegex() {
        return regex;
    }
}
