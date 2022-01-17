import Lexico.Lexer;
import Sintatico.Syntaxer;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.out.println(args.toString());
        Syntaxer sintatic = new Syntaxer("./Compilador/Testes/teste.txt");
        sintatic.program();
        System.out.println("Fim da análise sintática");
    }
}