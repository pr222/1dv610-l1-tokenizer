package Tokenizer;

// TODO: Make sure matching is maximal munched
// TODO: Throw exception when a token could not be matched (no valid rule)

public class Tokenizer {
    private Token activeToken;
    // TODO: Choose fields
    // - grammar-list
    // - left to match from input
    // - Default END-token?
    // - list of matched tokens so far

    public Tokenizer(GrammarRules grammar, String input) {
        // TODO: Instantiate fields
        // TODO: Match first token and make it the active token.
    }

    public void next() {
        // TODO: Match next token (return it immediatelly or let user ask for active?)
    }

    public void prevoius() {
        // TODO: Move acitve token one step back of the matched tokens.
    }

    public Token getActiveToken() {
        return activeToken;
    }
}
