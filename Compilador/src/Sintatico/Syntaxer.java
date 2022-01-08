package Sintatico;

import java.util.ArrayList;

import Utils.Token;
import Utils.Tag;

public class Syntaxer {
    private final ArrayList<Token> tokenList;
    private Token current;
    private int tokenIndex = 0;

    public Syntaxer(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
    }

    private void getNextToken(int expected) throws SyntaticError {
        if (this.current.tag == expected) {
            this.tokenIndex ++;
            this.current = tokenList.get(tokenIndex);
        }
        else {
            throw new SyntaticError("Expected " + expected + ", got " + this.current, tokenList.get(0).line);
        }
    }

    public void start() throws SyntaticError {
        program();
    }

    // program identifier begin [decl-list] stmt-list end "."
    private void program() throws SyntaticError {
        getNextToken(Tag.PROGRAM);
        getNextToken(Tag.IDENTIFIER);
        getNextToken(Tag.BEGIN);
        decl_list();
        stmt_list();
    }

    // decl ";" { decl ";"}
    private void decl_list() throws SyntaticError {
        decl();
        while (current.tag == Tag.SEMICOLON) {
            getNextToken(Tag.SEMICOLON);
            decl();
        }
        getNextToken(Tag.SEMICOLON);
    }

    // ident-list is type
    private void decl() throws SyntaticError {
        ident_list();
        getNextToken(Tag.IS);
        type();
    }

    // identifier {"," identifier}
    private void ident_list() throws SyntaticError {
        getNextToken(Tag.IDENTIFIER);
        while (current.tag == Tag.COMMA) {
            getNextToken(Tag.COMMA);
            getNextToken(Tag.IDENTIFIER);
        }
    }

    // int | float | char
    private void type() throws SyntaticError {
        switch (current.tag) {
            case Tag.INT -> getNextToken(Tag.INT);
            case Tag.FLOAT -> getNextToken(Tag.FLOAT);
            case Tag.CHAR -> getNextToken(Tag.CHAR);
            default -> throw new SyntaticError("Expected Type (int, float or char), got " + this.current, tokenList.get(0).line);
        }
    }

    // stmt {";" stmt}
    private void stmt_list() throws SyntaticError {
        stmt();
        while (current.tag == Tag.SEMICOLON) {
            getNextToken(Tag.SEMICOLON);
            stmt();
        }
    }

    // assign-stmt | if-stmt | while-stmt | repeat-stmt | read-stmt | write-stmt
    private void stmt() throws SyntaticError {
        switch (current.tag) {
            case Tag.IDENTIFIER -> assign_stmt();
            case Tag.IF -> if_stmt();
            case Tag.WHILE -> while_stmt();
            case Tag.REPEAT -> repeat_stmt();
            case Tag.READ -> read_stmt();
            case Tag.WRITE -> write_stmt();
            default -> throw new SyntaticError(this.current + " is an Invalid Statement Start", tokenList.get(0).line);
        }
    }

    // identifier "=" simple_expr
    private void assign_stmt() throws SyntaticError {
        getNextToken(Tag.IDENTIFIER);
        getNextToken(Tag.ASSIGN);
        simple_expr();
    }

    // if condition then stmt-list end | if condition then stmt-list else stmt-list end
    private void if_stmt() throws SyntaticError {
        getNextToken(Tag.IF);
        condition();
        getNextToken(Tag.THEN);
        stmt_list();
        if (current.tag == Tag.ELSE) {
            getNextToken(Tag.ELSE);
            stmt_list();
        }
        getNextToken(Tag.END);
    }

    // expression
    private void condition() throws SyntaticError {
        expression();
    }

    // repeat stmt-list stmt-suffix
    private void repeat_stmt() throws SyntaticError {
        getNextToken(Tag.REPEAT);
        stmt_list();
        stmt_suffix();
    }

    // until condition
    private void stmt_suffix() throws SyntaticError {
        getNextToken(Tag.UNTIL);
        condition();
    }

    // stmt-prefix stmt-list end
    private void while_stmt() throws SyntaticError {
        stmt_prefix();
        stmt_list();
        getNextToken(Tag.END);
    }

    // while condition do
    private void stmt_prefix() throws SyntaticError {
        getNextToken(Tag.WHILE);
        condition();
        getNextToken(Tag.DO);
    }

    // read "(" identifier ")"
    private void read_stmt() throws SyntaticError {
        getNextToken(Tag.READ);
        getNextToken(Tag.LEFT_PAR);
        getNextToken(Tag.IDENTIFIER);
        getNextToken(Tag.RIGHT_PAR);
    }

    // write "(" writable ")"
    private void write_stmt() throws SyntaticError {
        getNextToken(Tag.WRITE);
        getNextToken(Tag.LEFT_PAR);
        writable();
        getNextToken(Tag.RIGHT_PAR);
    }

    // simple-expr | literal
    private void writable() throws SyntaticError {
        simple_expr();
        getNextToken(Tag.LITERAL);
    }

    // simple-expr | simple-expr relop simple-expr
    private void expression() throws SyntaticError {
        simple_expr();
        switch (current.tag) {
            case Tag.EQUAL, Tag.GREATER, Tag.GREATER_EQUAL, Tag.LOWER, Tag.LOWER_EQUAL, Tag.NOT_EQUAL -> {
                relop();
                simple_expr();
            }
            default -> throw new SyntaticError(this.current + " is an Invalid Expression Operator", tokenList.get(0).line);
        }
    }

    // term | simple-expr addop term
    private void simple_expr() throws SyntaticError {
        term();
    }

    private void recursive_simple_expr() throws SyntaticError {
        switch (current.tag) {
            case Tag.ADD, Tag.SUB, Tag.OR -> {
                addop();
                term();
                recursive_simple_expr();
            }
            default -> {
            }
        }
    }

    // factor-a | term mulop factor-a
    private void term() throws SyntaticError {
        factor_a();
        recursive_term();
    }

    private void recursive_term() throws SyntaticError {
        switch (current.tag) {
            case Tag.MUL, Tag.DIV, Tag.AND -> {
                mulop();
                factor_a();
                recursive_term();
            }
            default -> {
            }
        }
    }

    // factor | ! factor | "-" factor
    private void factor_a() throws SyntaticError {

    }

    // identifier | constant | "(" expression ")"
    private void factor() throws SyntaticError {

    }

    // "==" | ">" | ">=" | "<" | "<=" | "!="
    private void relop() throws SyntaticError {

    }

    // "+" | "-" | ||
    private void addop() throws SyntaticError {

    }

    // "*" | "/" | &&
    private void mulop() throws SyntaticError {

    }

    // integer_const | float_const | char_const
    private void constant() throws SyntaticError {

    }

//    // digit+
//    private void integer_const() throws SyntaticError {
//
//    }
//
//    // digit+ “.”digit+
//    private void float_const() throws SyntaticError {
//
//    }
//
//    // " ‘ " carac " ’ "
//    private void char_const() throws SyntaticError {
//
//    }
//
//    // "{" caractere* "}"
//    private void literal() throws SyntaticError {
//
//    }
//
//    // letter (letter | digit | “_”)*
//    private void identifier() throws SyntaticError {
//
//    }
//
//    // [A-za-z]
//    private void letter() throws SyntaticError {
//
//    }
//
//    // [0-9]
//    private void digit() throws SyntaticError {
//
//    }
//
//    // um dos caracteres ASCII
//    private void carac() throws SyntaticError {
//
//    }
//
//    // um dos caracteres ASCII, exceto quebra de linha
//    private void caractere() throws SyntaticError {
//
//    }
}