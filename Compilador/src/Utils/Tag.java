package Utils;

public class Tag {
    public final static int 
        //Keywords
        PROGRAM           = 256, // program
        BEGIN            = 257, // begin
        END            = 258, // end  
        IS              = 259, // is
        IF              = 260, // if
        THEN            = 261, // then
        ELSE            = 262, // else
        REPEAT           = 263, // repeat
        UNTIL           = 264, // until
        WHILE           = 265, // while     
        DO              = 266, // do
        READ            = 267, // read
        WRITE           = 268, // write

        // Arithmetic Operators
        ADD             = 269, // +
        SUB             = 270, // -
        MUL             = 271, // *
        DIV             = 272, // /

        // Logical operators
        EQUAL           = 273, // ==
        GREATER         = 274, // >
        GREATER_EQUAL   = 275, // >=
        LOWER           = 276, // <
        LOWER_EQUAL     = 277, // <=
        NOT_EQUAL       = 278, // !=
        AND             = 279, // &&
        OR              = 280, // ||
        NOT             = 281, // !

        //Symbols
        
        DOT             = 282, //  .
        SEMICOLON       = 283, // ;
        COMMA           = 284, //  ,
        ASSIGN          = 285, //  =
        LEFT_PAR    = 286, // (
        RIGHT_PAR   = 287, // )
        SINGLE_QUOTES   = 288, // ''
        LEFT_BRACE      = 289, // {
        RIGHT_BRACE     = 290, // }

        //Types
        INT             = 291, // integer
        FLOAT           = 292, // float
        CHAR          = 293, // char

        INTEGER_CONSTANT= 294, // integer_cont
        FLOAT_CONSTANT   = 295, // float_const
        CHAR_CONSTANT   = 296, // char_const
        LITERAL         = 297, // literal
        IDENTIFIER      = 298, // identifier 
        LETTER          = 299, // letter
        DIGIT           = 300, // digit 
        CARAC        = 301, // caracteres ascii
        CARACTERE       = 302, // caractere ascii sem quebra de linha 

        OPEN_COMMENT    = 303, // /*
        CLOSE_COMMENT   = 304, // */
        COMMENT_BLOCK   = 305;  // /* */
}
