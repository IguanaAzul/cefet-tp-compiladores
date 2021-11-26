package Utils;

public class Token {
    public final int tag;
    public int linha;

    public Token(int tag, int linha) {
        this.tag = tag;
        this.linha = linha;
    }

    public Token(int tag) {
        this.tag = tag;
        this.linha = -1;
    }

    public String toString() {
        return String.valueOf(this.tag);
    }
}