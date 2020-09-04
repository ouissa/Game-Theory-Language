package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

public class Lexer {

    //List of reserved words.
    ArrayList<String> reservedWords = new ArrayList<String>(Arrays.asList("game","player","strategy","outcomes","associate","link","display","nash","dom_str","best_res"));

    // Constructor.
    public Lexer(){}


    //TRANSLATOR
    //Returns a tokenized line meant for the translator.
    public String lineTokenizerTranslator(String line){

        // The tokenized version of the line.
        String outputLine="";

        //Splitting the line by space as a delimiter.
        String[] lexemes = line.split(" ",0);

        //The first word determines how we're dealing with the rest of the line (parameters).
        switch(lexemes[0].toLowerCase()){
            case "game":
                outputLine=tokenizeLineTranslator("game",lexemes);
                break;

            case "player":
                outputLine=tokenizeLineTranslator("player",lexemes);
                break;

            case "strategy":
                outputLine=tokenizeLineTranslator("strategy",lexemes);
                break;

            case "outcomes":
                outputLine=tokenizeLineTranslator("outcomes",lexemes);
                break;

            case "associate":
                outputLine=tokenizeLineTranslator("associate",lexemes);
                break;

            case "link":
                outputLine=tokenizeLineTranslator("link",lexemes);
                break;

            case "display":
                outputLine=tokenizeLineTranslator("display",lexemes);
                break;

            case "nash":
                outputLine=tokenizeLineTranslator("nash",lexemes);
                break;

            case "dom_str":
                outputLine=tokenizeLineTranslator("dom_str",lexemes);
                break;

            case "best_res":
                outputLine=tokenizeLineTranslator("best_res",lexemes);
                break;

            //If a line doesn't start with one of the reserved words then its an ERROR.
            default:
                outputLine=tokenizeLineTranslator("ERROR",lexemes);
                System.err.println("ERROR:ILLEGAL START OF EXPRESSION");
                break;
        }
        return outputLine;
    }

    //Used when tokenizing for the Translator
    private String tokenizeLineTranslator(String word, String[] lexemes){

        //The string to be returned.
        String outputLine="";

        //This string constitues the second part of a statement (e.g. USERDEF:gm:outcomes1)
        String parameters = "USERDEF";

        //The first work should be a reserved word.
        outputLine=outputLine+word.toUpperCase()+" ";

        for(int i=1;i<lexemes.length;i++){
                parameters=parameters+":"+lexemes[i];
        }
        return outputLine+" "+parameters;
    }


}
