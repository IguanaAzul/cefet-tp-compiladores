package Utils;

public class Word extends Token{
	public String getLexeme() {
		return lexeme;
	}
	
	public int getTag() {
		return super(tag);
 	}
	private String lexeme = "";

	public Word (String s, int tag) {
		super(tag);
		lexeme = s;
	}

	public Word(String s, int tag, int line) {
		super(tag, line);
		lexeme = s;
	}

	public String toString(){
		return "" + lexeme;
	}
}
