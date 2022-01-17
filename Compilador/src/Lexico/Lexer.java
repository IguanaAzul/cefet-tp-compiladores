package Lexico;

import Utils.*;
import Utils.Float;

import java.io.*;
import java.util.*;



public class Lexer {

	public static int line = 1; //contador de linhas
	private char ch = ' '; //caractere lido do arquivo

	private final FileReader file;

	public Hashtable<String, Word> getWords() {
		return words;
	}

	public ArrayList<Token> getTokenList() {
		return token_list;
	}

	private final Hashtable<String, Word> words = new Hashtable<>();
	private final ArrayList<Token> token_list = new ArrayList<>();

	private void reserve(Word w){
		words.put(w.getLexeme(), w);
	}

	public Lexer(String fileName) throws IOException {
		try{
			file = new FileReader (fileName);
		}
		catch(FileNotFoundException e){
			System.out.println("Arquivo não encontrado");
			throw e;
		}

		reserve(new Word ("program", Tag.PROGRAM));
		reserve(new Word ("begin", Tag.BEGIN));
		reserve(new Word ("end", Tag.END));
		reserve(new Word ("is", Tag.IS));
		reserve(new Word ("if", Tag.IF));
		reserve(new Word ("then", Tag.THEN));
		reserve(new Word ("else", Tag.ELSE));
		reserve(new Word ("repeat", Tag.REPEAT));
		reserve(new Word ("until", Tag.UNTIL));
		reserve(new Word ("while", Tag.WHILE));
		reserve(new Word ("do", Tag.DO));
		reserve(new Word ("read", Tag.READ));
		reserve(new Word ("write", Tag.WRITE));
		reserve(new Word ("int", Tag.INT));
		reserve(new Word ("float", Tag.FLOAT));
		reserve(new Word ("char", Tag.CHAR));
	}

	/*Lê o próximo caractere do arquivo*/
	private void readch() throws IOException{
		ch = (char) file.read();
	}

	/* Lê o próximo caractere do arquivo e verifica se é igual a c*/
	private boolean readch(char c) throws IOException{
		readch();
		if (ch != c) {
			return false;
		}
		ch = ' ';
		return true;
	}

	public void readFile() throws Exception{
		for (Token token = scan(); token.tag != 65535; token = scan()){
			token_list.add(token);
			System.out.println("String: " + token.toString() + " Tag: " + token.tag + " Linha: " + token.line);
		}


	}

	public Token scan() throws Exception {
		//Desconsidera delimitadores na entrada
		for (;; readch()) {
			if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\b') {
			}
			else if (ch == '\n'){
				line++; //conta linhas
			}
			else break;
		}
		StringBuilder c = new StringBuilder();
		switch(ch){
			//Operadores
			case '&':
				if (readch('&')) return new Word ("&&", Tag.AND, line);
				else return new Token('&');
			case '|':
				if (readch('|')) return new Word ("||", Tag.OR, line);
				else return new Token('|');
			case '!':
				if (readch('=')) return new Word ("!", Tag.NOT, line);
				else return new Word ("!=", Tag.NOT_EQUAL, line);
			case '=':
				if (readch('=')) return new Word ("==", Tag.EQUAL, line);
				else return new Token('=');
			case '<':
				if (readch('=')) return new Word ("<=", Tag.LOWER_EQUAL, line);
				else return new Word ("<", Tag.LOWER, line);
			case '>':
				if (readch('=')) new Word (">=", Tag.GREATER_EQUAL, line);
				else return new Word (">", Tag.GREATER, line);
			case '\'':
				readch();
				if(ch!='\''){
					c.append(ch);
					readch();
				}
				if(ch=='\''){
					String s = c.toString();
					Word w = words.get(s);
					if( w != null )
						return w;
					w = new Word(s, Tag.CHAR_CONSTANT, line);
					words.put(s, w);
					return w;
				}
				else throw new Exception("Não é um caractere válido");
			case '{':
				readch();
				while(ch!='}'){
					c.append(ch);
					if (ch == '\n' || ch == '\t' || ch == '\r' || ch == '\b') throw new Exception("Literal mal formado");
					readch();
				}
				readch();
				String s = c.toString();
				Word w = words.get(s);
				if( w != null )
					return w;
				w = new Word(s, Tag.LITERAL, line);
				words.put(s, w);
				return w;
			case'/':
				readch();
				if(ch=='*'){
					while(true){
						readch();
						if(ch == '*'){
							readch();
							while(ch == '*'){
								c.append(ch);
								readch();
							}
							if(ch=='/')
								break;
							else c.append('*');
						}
						if((int)ch==65535)
							throw new Exception("Um comentário não foi fechado");
						c.append(ch);
					}
					s = c.toString();
					w = words.get(s);
					if( w != null )
						return w;
					w = new Word(s, Tag.COMMENT_BLOCK, line);
					words.put(s, w);
					return w;
				}
			case ';':
				readch();
				s = c.toString();
				w = new Word(s, Tag.SEMICOLON, line);
				words.put(s, w);
				return w;
		}
		//Números
		if (Character.isDigit(ch)){
			int value=0;
			do{
				value = 10*value + Character.digit(ch,10);
				readch();
			}
			while(Character.isDigit(ch));
			if(ch!='.') return new Num(value);

			float aux = 10;
			float float_value = 0;
			for(;;) {
				readch();
				if( !Character.isDigit(ch) ) break;
				float_value += (Character.digit(ch, 10) / 10.0);
				aux = aux*10;
			}
			return new Float(float_value);
		}
		//Identificadores
		if (Character.isLetter(ch)){
			StringBuilder sb = new StringBuilder();
			do{
				sb.append(ch);
				readch();
			}
			while(Character.isLetterOrDigit(ch));
			String s = sb.toString();
			Word w = words.get(s);
			if (w != null) return w; //palavra já existe na HashTable
			w = new Word (s, Tag.IDENTIFIER, line);
			words.put(s, w);
			return w;
		}
		//Caracteres não especificados
		Token t = new Token(ch);
		ch = ' ';
		return t;
	}
}
