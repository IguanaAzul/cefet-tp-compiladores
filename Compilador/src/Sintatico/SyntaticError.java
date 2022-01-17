package Sintatico;

public class SyntaticError extends Exception{
    private final String error;
    private final int line;

    public SyntaticError(String error, int line){
        super();
        this.error = error;
        this.line = line;
    }

    @Override
    public String getMessage() {
        return "Syntatic Error: " + this.error + "\tAt line: " + this.line;
    }
}
