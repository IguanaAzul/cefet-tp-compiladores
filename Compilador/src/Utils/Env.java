package Utils;
import java.util.*;

public class Env {
	private final Hashtable<Token, Tag> table;
	protected Env prev;

	public Env(Env n) {
		table = new Hashtable<>();
		prev = n;
	}

	public void put(Token w, Tag i){
		table.put(w,i);
	}

	public Tag get(Token w){
		for (Env e = this; e!=null; e = e.prev){
			Tag found = e.table.get(w);
			if (found != null)
				return found;
		}
		return null;
	}
}
