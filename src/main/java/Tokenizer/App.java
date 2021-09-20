package Tokenizer;

public class App {
    public static void main(String[] args) {
        System.out.println("Time to tokenize!");

        GrammarRules gramm = new GrammarRules();

        TokenRule word = new TokenRule("WORD", "^[\\w|åäöÅÄÖ]+");

        gramm.addRule(word);

        System.out.println(gramm.getRules().get(0).getName());
        System.out.println(gramm.getRules().get(0).getRegex());

        Tokenizer tokenizer = new Tokenizer(gramm, "a");

        System.out.println("CREATED");
        System.out.println(tokenizer.getActiveToken().getType());
        System.out.println(tokenizer.getActiveToken().getValue());

        tokenizer.next();
        System.out.println("ONE NEXT");
        System.out.println(tokenizer.getActiveToken().getType());
        System.out.println(tokenizer.getActiveToken().getValue());
/*
        tokenizer.previous();
        tokenizer.previous();
        System.out.println("TWO PREVIOUS");
        System.out.println(tokenizer.getActiveToken().getType());
        System.out.println(tokenizer.getActiveToken().getValue());
*/


        tokenizer.next();
        tokenizer.next();
        System.out.println("TWO NEXT");
        System.out.println(tokenizer.getActiveToken().getType());
        System.out.println(tokenizer.getActiveToken().getValue());

    }
}
