package Utils;

public class Lexema extends Token {
    private String lexema = "";

    public Lexema(String str, int tag, int linha) {
        super(tag, linha);
        lexema = str;
    }

    public Lexema(String str, int tag) {
        super(tag);
        lexema = str;
    }

    public String getLexema() {
        return this.lexema;
    }
}