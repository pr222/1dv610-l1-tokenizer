# Tokenizer
## About
The Tokenizer class takes in a grammar built by a set of grammar rules. Each rule concists of a token with a name and a specified regex. The regex is used by the Tokenizer to match the input string. From that match the tokenizer will then create a new token with the value of the match and the type that corresponds to the rule's identified name.

It also takes in a string input to be tokenized. The whole input won't immediatelly be matched and tokenized at the creation of Tokenizer. Instead, it will only try to tokenize the first possible match. By calling next() on the tokenizer you go one step forward and make a new match if there is still input left to tokenize. Reaching a token with the type 'END' tells you that there is nothing left to tokenize.

Calling getActiveToken() returns the token on the current position.

Nothing happens when trying to call next() further when already being on the 'END'-token, you are still on the same token. That is also the case for trying to go beyond the starting point when calling previous() too many times, you are then still left on the firstly matched token.

An exception is thrown whenever the input could not be matched by any of the available token rules.

## Example

### Build the grammar...
````
    GrammarRules wordAndDot = new GrammarRules();

    TokenRule word = new TokenRule("WORD", "^[\\w|åäöÅÄÖ]+");
    wordAndDot.addRule(word);

    TokenRule dot = new TokenRule("DOT", "^\\.");
    wordAndDot.addRule(dot);``
````


### ...Instantiate Tokenizer...

````
    Tokenizer tokenizer = new Tokenizer(wordAndDot, "aa.b");
````

### ...Use and step in the Tokenizer
````
    System.out.println(tokenizer.getActiveToken().getType());   // -> "WORD"
    System.out.println(tokenizer.getActiveToken().getType());   // -> "aa"
    tokenizer.next();
    System.out.println(tokenizer.getActiveToken().getType());   // -> "DOT"
    System.out.println(tokenizer.getActiveToken().getType());   // -> "."
````

## Class diagram
![class-diagram-tokenizer](./images/Tokenizer-class-diagram-2.png)