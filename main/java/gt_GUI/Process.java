/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt_GUI;

import parser.Lexer;
import parser.Parser;
import lang_classes.Game;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import lang_classes.NashNotFoundException;
import lang_classes.Pair;
import lang_classes.Player;

/**
 *
 * @author supernova
 */
public class Process {
    public static void parseThing() throws IOException{
        
        // The path to the text file containing the source code.
        
        String PATH = System.getProperty("user.home") + "/code.txt";

        //File destined for the translator.
        String translatorFilePath= System.getProperty("user.home") + "/translatorFile.txt";

        //A buffer to read through the file.
        BufferedReader reader;

        Lexer lexer= new Lexer();

        String tokenizedLine;
        String lineToBeTraslated;

        //File writer to the translatorFile.txt
        FileWriter translatorFileWriter = new FileWriter(translatorFilePath, false);

        //Flag that is set to false in case of a parsing error.
        boolean flag=true;

        try {
            reader = new BufferedReader(new FileReader(PATH));
            String line = reader.readLine();
            Parser parser=new Parser();

            //Checking whether the read line is empty of a comment (starting with '//').
            while (line != null && flag) {
                if(!line.isEmpty() && !isComment(line)) {
                    //parser
                    //System.out.println(line);
                    //System.out.println("\t"+parser.parseLine(line));

                    //If approved by the parser, the line will be written to translatorFile.txt, else the program will stop.
                    if(parser.parseLine(line)){
                        //TRANSLATOR
                        //Tokenize the line and writes it to the file destined for the TRANSLATOR.
                        lineToBeTraslated = lexer.lineTokenizerTranslator(line);
                        translatorFileWriter.write(lineToBeTraslated);
                        translatorFileWriter.write("\n");
                    }else{
                        //Parsing has failed. program need to stop.
                        System.err.println("PARSING ERROR: PROGRAM STOPPED");
                        flag=false;
                    }
                }
                // read next line
                line = reader.readLine();
            }
            reader.close();
            translatorFileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        //For any further processing flag must be checked (parsing went without any issues).
        if(flag){

            System.out.println("SUCCESSFUL PARSING");

        }

    }

    //Function that takes a line and determines whether it's a comment (starts with '//') or not.
    public static boolean isComment(String line){
        char[] ch = line.toCharArray();
        if(ch[0]=='/' && ch[1]=='/'){
            return true;
        }
        return false;
    }
    
    
       
    }
    
    

