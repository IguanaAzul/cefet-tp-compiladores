package Semantico;

public class SemanticError extends Exception{
    private final String error;
    private final int line;

    public SemanticError(String error, int line){
        super();
        this.error = error;
        this.line = line;
    }

    @Override
    public String getMessage() {
        return "Semantic Error: " + this.error + "\tAt line: " + this.line;
    }
}
