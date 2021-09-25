# Mall för inlämning laboration 1, 1dv610

## Checklista
- [x] I min tokeniserare finns inga tokentyper eller reg-exp. Dessa finns i mitt testprojekt eftersom de skapas utav användaren.
- [x] Jag har skrivit all kod och reflektioner själv. Jag har inte använt mig av andras kod för att lösa uppgiften.
- [x] Mina testresultat är skrivna utifrån utförd testning ( och inte teoretiskt, det bör fungera :) )
- [x] De enda statiska metoder eller funktioner utanför klasser som jag har är för att starta upp min testapplikation ex main(java).
- [x] De enda bibliotek och färdiga klasser som används är sådana som måste användas (eller som används för att testa modulen).

## Egenskattning och mål
- [ ] Jag är inte klar eftersom jag vet att jag saknar något. Då skall du inte lämna in!
- [x] Jag eftersträvar med denna inlämning godkänt betyg (E-D)
    - [x] De flesta testfall fungerar
    - [x] Koden är förberedd på Återanvändning
    - [x] All kod samt historik finns i git
    - [  ] Kodkvalitetskraven är ifyllda
    - [  ] Reflektion är skriven
- [ ] Jag eftersträvar med denna inlämning högre betyg (C-B) och anser mig uppfylla alla extra krav för detta.
    - [x] Samtliga testfall är skrivna
    - [x] Egna testfall för Maximal munch och kantfall
    - [x] Testfall är automatiserade
    - [x] Det finns en tydlig beskrivning i hur modulen skall användas (i git)
    - [  ] Kodkvalitetskraven är varierade
- [ ] Jag eftersträvar med denna inlämning högsta betyg (A)

Förtydligande: Examinator kommer sätta betyg oberoende på vad ni anser.

## Återanvändning
Beskrivning för en ny användare utav Tokenizer: [README.md](./README.md)

## Beskrivning av min kod
Tokenizern tar in en input-sträng som kan tokenizeras och en grammatik fylld med olika regler. Reglerna har regex-uttryck som sedan används för att matchas mot inputen och utifrån matchningarna skapar Tokenizern Tokens. Ett token innehåller då värdet som matchningen resulterade i samt benämning på typ av matching utifrån regelns namn.

Genom metoden getActiveToken() kan man få ut tokenet i den positionen som tokenizeraren befinner sig i för tillfället. Genom metoderna next() och previous() kan man förflytta den positionen och varje gång next() anropas så körs även en ny matchning om det finns något kvar att matcha utav inputen. 

![Tokenizer-class-diagram](./images/Tokenizer-class-diagram-2.png)

## Hur jag testat
Testning har gjorts genom att först skapa automatiska tester som sedan utnyttjats under utvecklingens gång. Jag har även testat explorativt i App-classens main-funktion genom att logga ut saker men dessa har tagits bort allteftersom.

Länk till test-klass: [TestTokenizer](https://gitlab.lnu.se/1dv610/student/pr222ja/l1/-/blob/master/src/test/java/Tokenizer/TokenizerTest.java)

### Testfall
| Namn      | Grammatik  | Sträng | Sekvens | Förväntat Aktivt Token | PASS/FAIL |
| --------- | ---------- | ------ | ------- | ------------ | --------- |
| TC1       | WordAndDot | "a"    | []      | WORD "a"     | PASS      |
| TC2       | WordAndDot | "a aa" | [>]     | WORD "aa"    | PASS      |
| TC3       | WordAndDot | "a.b"  | [>]     | DOT "."      | PASS      |
| TC4       | WordAndDot | "a.b"  | [>>]    | **WORD "b"** | PASS      |
| TC5       | WordAndDot | "aa. b" | **[>>]** | WORD "b"   | PASS      |
| TC6       | WordAndDot | "a .b" | [>><]   | DOT "."      | PASS      |
| TC7       | WordAndDot | ""     | []      | END **""**   | PASS      |
| TC8       | WordAndDot | " "    | []      | **END ""**   | PASS      |
| TC9       | WordAndDot | "a"    | **[>]** | END **""**   | PASS      |
| TC10      | WordAndDot | "a"    | [<]     | **WORD "a"** | PASS      |
| TC11      | WordAndDot | "!"    | []      | Exception    | PASS      |
| TC12      | Arithmetic | "3"    | []      | NUMBER "3"   | PASS      |
| TC13      | Arithmetic | "3.14" | []      | NUMBER "3.14" | PASS     |
| TC14      | Arithmetic | "3 + 54 * 4" | [>>>] | MUL "*"  | PASS      |
| TC15      | Arithmetic | "3+5 # 4" | [>>>] | **Exception** | PASS    |
| TC16      | Arithmetic | "3.0+54.1     + 4.2" | [><>>>] | **ADD "+"** | PASS      |

Kommentar: Kastandet utav undantag när en matchning inte kunde göras enligt givna regler körs inte i samband med anrop till aktiv token. Kastandet görs redan vid förflyttningen i samband med anrop till nästa token. Därav är det exempelvis inte möjligt att flytta sig till 'END'-tokenet utan att undantag kastas, även om man aldrig anropar efter aktivt token.

### Testfall för högre betyg
| Namn      | Grammatik  | Sträng | Sekvens | Förväntat Aktivt Token | PASS/FAIL |
| --------- | ---------- | ------ | ------- | ------------ | --------- |
| TC17_ForcingPastEnd | Arithmetic | "3.0+54.1+4.2" | [>>>>>>] | END "" | PASS      |
| TC18_MaxMunch | MaxMunch | "3.0 54.1" | [>] | FLOAT "54.1" | PASS      |

### Samma resultat visat utav Gradle:
![Test-results](./images/test-results-2021-09-24.png)

## Kodkvalitetskrav

**Fetmarkera** de "regler" som används ur CC. Ni kan frångå tabellformat om ni vill. Skapa direktlänkar till er kod där det är lämpligt.

### Namngivning
6.
| Namn och förklaring  | Reflektion                                   |
| -------------------  | ---------------------------------------------|
| Tokenizer            |                                              |
| next()               |                                              |
| previous()           |                                              |
| getActiveToken()     |                                              |
|                      |                                              |

### Funktioner
7.
| Metodnamn och förklaring  | Reflektion                              |
| -------------------  | ---------------------------------------------|
| matchToken()              |                                              |
| match()                     |                                              |
| maxMunch()                     |                                              |
| tokenize()                     |                                              |
| next()                     |                                              |


## Laborationsreflektion
8. Reflektera över uppgiften utifrån ett kodkvalitetsperspektiv. Använd begrepp ifrån boken. 
