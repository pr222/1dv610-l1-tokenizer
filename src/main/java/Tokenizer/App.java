package Tokenizer;

public class App {
    public static void main (String[] args) {
        System.out.println("Time to tokenize!");

        GrammarRules gramm = new GrammarRules();

        TokenRule word = new TokenRule("WORD", "^[\\w|åäöÅÄÖ]+");
        gramm.addRule(word);
        TokenRule dot = new TokenRule("DOT", "^\\.");
        gramm.addRule(dot);

        System.out.println(gramm.getRules().get(0).getName());
        System.out.println(gramm.getRules().get(0).getRegex());

       //  Tokenizer tokenizer = new Tokenizer(gramm, "a");
/*
        System.out.println("CREATED");
        System.out.println(tokenizer.getActiveToken().getType());
        System.out.println(tokenizer.getActiveToken().getValue());

        tokenizer.next();
        System.out.println("ONE NEXT");
        System.out.println(tokenizer.getActiveToken().getType());
        System.out.println(tokenizer.getActiveToken().getValue());

        tokenizer.previous();
        tokenizer.previous();
        System.out.println("TWO PREVIOUS");
        System.out.println(tokenizer.getActiveToken().getType());
        System.out.println(tokenizer.getActiveToken().getValue());



        tokenizer.next();
        tokenizer.next();
        System.out.println("TWO NEXT");
        System.out.println(tokenizer.getActiveToken().getType());
        System.out.println(tokenizer.getActiveToken().getValue());
*/

        try {
            Tokenizer tizer = new Tokenizer(gramm, "aaa aaa");

            System.out.println("CONSTRUCT: " + tizer.getActiveToken().getValue() + " " + tizer.getActiveToken().getType());
            tizer.next();
            Token active = tizer.getActiveToken();
            System.out.println("FIRST NEXT: " + tizer.getActiveToken().getValue() + " " + tizer.getActiveToken().getType());
            tizer.next();
            System.out.println("SECOND NEXT: " + tizer.getActiveToken().getValue() + " " + tizer.getActiveToken().getType());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
