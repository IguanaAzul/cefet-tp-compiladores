package Sintatico;

import java.util.ArrayList;

import Utils.Token;
import Utils.Tag;

import java.io.IOException;

public class Syntaxer {
    private final ArrayList<Token> tokenList;
    private Token current;
    private int tokenIndex = 0;

    public Syntaxer(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
    }

    private void getNextToken(int expected) throws IOException, SyntaticError {
        if (this.current.tag == expected) {
            this.tokenIndex ++;
            this.current = tokenList.get(tokenIndex);
        }
        else {
            throw new SyntaticError("Expected " + expected + " got " + this.current, tokenList.get(0).line);
        }
    }

    public void start() throws IOException, SyntaticError {
        program();
    }

    // program identifier begin [decl-list] stmt-list end "."
    private void program() throws IOException, SyntaticError {
        getNextToken(Tag.PROGRAM);
        getNextToken(Tag.IDENTIFIER);
        body();
    }

    private void body() throws IOException, SyntaticError {
        decl_list();
    }

    // decl ";" { decl ";"}
    private void decl_list() throws IOException, SyntaticError {

    }

    // ident-list is type
    private void decl() throws IOException, SyntaticError {

    }

    // identifier {"," identifier}
    private void ident_list() throws IOException, SyntaticError {

    }

    // int | float | char
    private void type() throws IOException, SyntaticError {

    }

    // stmt {";" stmt}
    private void stmt_list() throws IOException, SyntaticError {

    }

    // assign-stmt | if-stmt | while-stmt | repeat-stmt | read-stmt | write-stmt
    private void stmt() throws IOException, SyntaticError {

    }

    // identifier "=" simple_expr
    private void assign_stmt() throws IOException, SyntaticError {

    }

    // if condition then stmt-list end | if condition then stmt-list else stmt-list end
    private void if_stmt() throws IOException, SyntaticError {

    }

    // expression
    private void condition() throws IOException, SyntaticError {

    }

    // repeat stmt-list stmt-suffix
    private void repeat_stmt() throws IOException, SyntaticError {

    }

    // until condition
    private void stmt_suffix() throws IOException, SyntaticError {

    }

    // stmt-prefix stmt-list end
    private void while_stmt() throws IOException, SyntaticError {

    }

    // while condition do
    private void stmt_prefix() throws IOException, SyntaticError {

    }

    // read "(" identifier ")"
    private void read_stmt() throws IOException, SyntaticError {

    }

    // write "(" writable ")"
    private void write_stmt() throws IOException, SyntaticError {

    }

    // simple-expr | literal
    private void writable() throws IOException, SyntaticError {

    }

    // simple-expr | simple-expr relop simple-expr
    private void expression() throws IOException, SyntaticError {

    }

    // term | simple-expr addop term
    private void simple_expr() throws IOException, SyntaticError {

    }

    // factor-a | term mulop factor-a
    private void term() throws IOException, SyntaticError {

    }

    // factor | ! factor | "-" factor
    private void factor_a() throws IOException, SyntaticError {

    }

    // identifier | constant | "(" expression ")"
    private void factor() throws IOException, SyntaticError {

    }

    // "==" | ">" | ">=" | "<" | "<=" | "!="
    private void relop() throws IOException, SyntaticError {

    }

    // "+" | "-" | ||
    private void addop() throws IOException, SyntaticError {

    }

    // "*" | "/" | &&
    private void mulop() throws IOException, SyntaticError {

    }

    // integer_const | float_const | char_const
    private void constant() throws IOException, SyntaticError {

    }

    // digit+
    private void integer_const() throws IOException, SyntaticError {

    }

    // digit+ “.”digit+
    private void float_const() throws IOException, SyntaticError {

    }

    // " ‘ " carac " ’ "
    private void char_const() throws IOException, SyntaticError {

    }

    // "{" caractere* "}"
    private void literal() throws IOException, SyntaticError {

    }

    // letter (letter | digit | “_”)*
    private void identifier() throws IOException, SyntaticError {

    }

    // [A-za-z]
    private void letter() throws IOException, SyntaticError {

    }

    // [0-9]
    private void digit() throws IOException, SyntaticError {

    }

    // um dos caracteres ASCII
    private void carac() throws IOException, SyntaticError {

    }

    // um dos caracteres ASCII, exceto quebra de linha
    private void caractere() throws IOException, SyntaticError {

    }
}