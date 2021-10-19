package Tokenizer;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenMatchRule {
    String name;
    String regex;

    public TokenMatchRule(String name, String regex) {
        this.name = name;
        this.regex = regex;
    }

    public Token matchToken(String toMatch) {
        String value = "";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(toMatch);

        boolean found = matcher.find();

        if(found) {
            MatchResult result = matcher.toMatchResult();

            value = toMatch.substring(result.start(), result.end());
        }

        return new Token(name, value);
    }
}
