import Lexico.Lexer;
import Sintatico.Syntaxer;
import Utils.Word;
import Utils.Token;

import java.util.ArrayList;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) throws Exception {
        Lexer lexer = new Lexer("./Compilador/Testes/teste1.txt");
        lexer.readFile();
        ArrayList<Token> token_list = lexer.getTokenList();
        Hashtable<String, Word> words = lexer.getWords();
        Syntaxer sintatic = new Syntaxer(token_list, words);
        sintatic.program();
        System.out.println("Fim da análise sintática");
    }
}