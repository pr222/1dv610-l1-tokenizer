package Tokenizer;

public class TokenRule {
    String name;
    String regex;

    public TokenRule(String name, String regex) {
        this.name = name;
        this.regex = regex;
    }

    public String getName() {
        return name;
    }

    public String getRegex() {
        return regex;
    }
}
