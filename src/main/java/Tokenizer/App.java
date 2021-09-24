package Tokenizer;

public class App {
    public static void main (String[] args) {
        try {
            System.out.println("Time to tokenize!");

            GrammarRules wordAndDot = new GrammarRules();

            TokenRule word = new TokenRule("WORD", "^[\\w|åäöÅÄÖ]+");
            wordAndDot.addRule(word);
            TokenRule dot = new TokenRule("DOT", "^\\.");
            wordAndDot.addRule(dot);

            System.out.println();
            System.out.println("__________________________________________");
            System.out.println("Rule: " + wordAndDot.getRules().get(0).getName());
            System.out.println("Regex: " + wordAndDot.getRules().get(0).getRegex());
            System.out.println("Rule: " + wordAndDot.getRules().get(1).getName());
            System.out.println("Regex: " + wordAndDot.getRules().get(1).getRegex());
            System.out.println("***************");

            Tokenizer tizer = new Tokenizer(wordAndDot, "a aa");
            System.out.println("INPUT: 'a aa'");
            System.out.println("ACTIVE WHEN INITIALIZED: " + tizer.getActiveToken().getValue() + " " + tizer.getActiveToken().getType());
            tizer.next();
            System.out.println("FIRST NEXT: " + tizer.getActiveToken().getValue() + " " + tizer.getActiveToken().getType());
            tizer.next();
            System.out.println("SECOND NEXT: " + tizer.getActiveToken().getValue() + " " + tizer.getActiveToken().getType());
            tizer.next();
            System.out.println("THIRD NEXT: " + tizer.getActiveToken().getValue() + " " + tizer.getActiveToken().getType());
            tizer.previous();
            tizer.previous();
            tizer.previous();
            tizer.previous();
            System.out.println("4 PREVIOUS: " + tizer.getActiveToken().getValue() + " " + tizer.getActiveToken().getType());
            System.out.println();

            Tokenizer tokenizer = new Tokenizer(wordAndDot, "aa.. b");
            System.out.println("INPUT 'aa.. b'");
            System.out.println("ACTIVE WHEN INITIALIZED: " + tokenizer.getActiveToken().getValue() + " " + tokenizer.getActiveToken().getType());
            tokenizer.next();
            System.out.println("FIRST NEXT: " + tokenizer.getActiveToken().getValue() + " " + tokenizer.getActiveToken().getType());
            tokenizer.next();
            System.out.println("SECOND NEXT: " + tokenizer.getActiveToken().getValue() + " " + tokenizer.getActiveToken().getType());
            tokenizer.next();
            System.out.println("THIRD NEXT: " + tokenizer.getActiveToken().getValue() + " " + tokenizer.getActiveToken().getType());

            // Tokenizer t1 = new Tokenizer(wordAndDot, "!");

            GrammarRules arithmetic = new GrammarRules();

            TokenRule number = new TokenRule("NUMBER", "^[0-9]+(\\.([0-9])+)?");
            arithmetic.addRule(number);

            TokenRule add = new TokenRule("ADD", "^[+]");
            arithmetic.addRule(add);

            TokenRule mul = new TokenRule("MUL", "^[*]");
            arithmetic.addRule(mul);

            System.out.println();
            System.out.println("__________________________________________");
            System.out.println("Rule: " + arithmetic.getRules().get(0).getName());
            System.out.println("Regex: " + arithmetic.getRules().get(0).getRegex());
            System.out.println("Rule: " + arithmetic.getRules().get(1).getName());
            System.out.println("Regex: " + arithmetic.getRules().get(1).getRegex());
            System.out.println("Rule: " + arithmetic.getRules().get(2).getName());
            System.out.println("Regex: " + arithmetic.getRules().get(2).getRegex());
            System.out.println("***************");

            String in = "3.32 88 42.3";
            Tokenizer tokeniz = new Tokenizer(arithmetic, in);
            System.out.println("INPUT: " + in);
            System.out.println("ACTIVE WHEN INITIALIZED: " + tokeniz.getActiveToken().getValue() + " " + tokeniz.getActiveToken().getType());
            tokeniz.next();
            System.out.println("FIRST NEXT: " + tokeniz.getActiveToken().getValue() + " " + tokeniz.getActiveToken().getType());
            tokeniz.next();
            System.out.println("SECOND NEXT: " + tokeniz.getActiveToken().getValue() + " " + tokeniz.getActiveToken().getType());
            tokeniz.next();
            System.out.println("THIRD NEXT: " + tokeniz.getActiveToken().getValue() + " " + tokeniz.getActiveToken().getType());

            GrammarRules maxMunch = new GrammarRules();

            TokenRule integer = new TokenRule("INTEGER", "^[0-9]+");
            maxMunch.addRule(integer);

            TokenRule flo = new TokenRule("FLOAT", "^[0-9]+\\.[0-9]+");
            maxMunch.addRule(flo);

            System.out.println();
            System.out.println("__________________________________________");
            System.out.println("Rule: " + maxMunch.getRules().get(0).getName());
            System.out.println("Regex: " + maxMunch.getRules().get(0).getRegex());
            System.out.println("Rule: " + maxMunch.getRules().get(1).getName());
            System.out.println("Regex: " + maxMunch.getRules().get(1).getRegex());
            System.out.println("***************");

            String inp = "3.32 5 99 4.23";
            Tokenizer t = new Tokenizer(maxMunch, inp);
            System.out.println("INPUT: " + inp);
            System.out.println("ACTIVE WHEN INITIALIZED: " + t.getActiveToken().getValue() + " " + t.getActiveToken().getType());
            t.next();
            System.out.println("FIRST NEXT: " + t.getActiveToken().getValue() + " " + t.getActiveToken().getType());
            t.next();
            System.out.println("SECOND NEXT: " + t.getActiveToken().getValue() + " " + t.getActiveToken().getType());
            t.next();
            System.out.println("THIRD NEXT: " + t.getActiveToken().getValue() + " " + t.getActiveToken().getType());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
