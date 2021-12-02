package Utils;

public class Token {
    public final int tag;
    public int line;

    public Token(int t) {
        tag = t; 
    }

    public Token(int t, int line) {
        this.tag = t;
        this.line = line;
    }

    public String toString() {
        return String.valueOf(tag);
    }
}