package Tokenizer;

public class App {
    public static void main(String[] args) {
        System.out.println("Time to tokenize!");

        GrammarRules gramm = new GrammarRules();

        TokenRule word = new TokenRule("WORD", "^[\\w|åäöÅÄÖ]+");

        gramm.addRule(word);

        System.out.println(gramm.getRules().get(0).getName());
        System.out.println(gramm.getRules().get(0).getRegex());
    }
}
