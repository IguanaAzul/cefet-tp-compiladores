import Lexico.Lexer;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.out.println(args.toString());
        Lexer lexer = new Lexer("teste.txt");
        lexer.readFile();
        System.out.println(lexer.getWords().toString());
    }
}