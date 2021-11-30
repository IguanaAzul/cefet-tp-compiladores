package Utils;

public class Word extends Token{
	public String getLexeme() {
		return lexeme;
	}

	private String lexeme = "";

	 public static final Word eq = new Word ("==", Tag.EQUAL);
	 public static final Word gr = new Word (">", Tag.GREATER);
	 public static final Word ge = new Word (">=", Tag.GREATER_EQUAL);
	 public static final Word lo = new Word ("<", Tag.LOWER);
	 public static final Word le = new Word ("<=", Tag.LOWER_EQUAL);
	 public static final Word ne = new Word ("!=", Tag.NOT_EQUAL);
	 public static final Word and = new Word ("&&", Tag.AND);
	 public static final Word or = new Word ("||", Tag.OR);
	 public static final Word no = new Word ("!", Tag.NOT);

	 public Word (String s, int tag){
		 super(tag);
		 lexeme = s;
	 }
	 public String toString(){
		return "" + lexeme;
	 }
}
