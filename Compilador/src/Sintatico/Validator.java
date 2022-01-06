package Sintatico;

import Utils.Tag;
import Utils.Token;

import java.util.ArrayList;

public class Validator {
    private static final String PROGRAM_BEGIN_ERROR_MESSAGE = "Code doesnt start with 'PROGRAM'.";
    private static final String PROGRAM_END_ERROR_MESSAGE = "Code doesnt finish with 'END.'.";
    private final ArrayList<Token> tokenList;

    public Validator(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public void validateProgramStartAndFinish() throws SyntaticError {
        Token firstToken = tokenList.get(0);
        Token lastToken = tokenList.get(tokenList.size() - 1);
        if (firstToken.tag != Tag.PROGRAM) {
            throw new SyntaticError(PROGRAM_BEGIN_ERROR_MESSAGE, firstToken.line);
        } else if (lastToken.tag != Tag.END) {
            throw new SyntaticError(PROGRAM_END_ERROR_MESSAGE, lastToken.line);
        }
    }

    public void validateProgram() {
    }
}
