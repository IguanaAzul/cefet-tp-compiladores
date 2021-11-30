package Utils;

public class Float extends Token{
	 public final float value;

	 public Num(float value){
		 super(Tag.FLOAT);
		 this.value = value;
	 }

	 public String toString(){
		return "" + value;
	 }
}