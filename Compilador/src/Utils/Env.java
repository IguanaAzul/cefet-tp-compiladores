package Utils;
import java.util.*;

public class Env {
	private Hashtable table; //tabela de s�mbolos do ambiente
	protected Env prev; //ambiente imediatamente superior

	public Env(Env n) {
		table = new Hashtable(); //cria a TS para o ambiente
		prev = n; //associa o ambiente atual ao anterior
	}
	/*Este método insere uma entrada na TS do ambiente */
	/*A chave da entrada � o Token devolvido pelo analisador l�xico */
	/*Id � uma classe que representa os dados a serem armazenados na TS para */
	/*identificadores */
	public void put(Token w, Id i){
		table.put(w,i);
	}

	/*Este método retorna as informa��es (Id) referentes a determinado Token */
	/*O Token � pesquisado do ambiente atual para os anteriores */
	public Id get(Token w){
		for (Env e = this; e!=null; e = e.prev){
			Id found = (Id) e.table.get(w);
			if (found != null) //se Token existir em uma das TS
				return found;
		}
		return null; //caso Token n�o exista em uma das TS
	}
}
