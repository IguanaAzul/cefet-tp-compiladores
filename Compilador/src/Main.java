import Lexico.Lexer;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.out.println(args.toString());
        Lexer lexer = new Lexer("Insira aqui seu txt");
        lexer.readFile();
        System.out.println(lexer.words.toString());  
    }
}