/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt_GUI;

/**
 *
 * @author supernova
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import lang_classes.Game;
import lang_classes.NashNotFoundException;
import lang_classes.Pair;
import lang_classes.Player;

public class Translator {

    //Constructor
    public void Translator(){}

    //A table of all the players initiated.
    public static Hashtable<String,Player> players= new Hashtable<>();

    //A table of strategies.
    public static Hashtable<String,ArrayList<String>> strategies= new Hashtable<>();

    //A table of games.
    public static Hashtable<String,Game> games= new Hashtable<String,Game>();

    //A table of outcomes
    public static Hashtable<String,ArrayList<ArrayList<Pair>>> outcomes= new Hashtable<>();

    public void translate(String filePath){
        BufferedReader reader;
        FileWriter writer;
        try {
           
            String outputFilePath = System.getProperty("user.home") + "/output1.txt";
            
            reader = new BufferedReader(new FileReader (filePath));
            writer = new FileWriter(outputFilePath);
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line + "I am from the translator file");

                //Processing the line
                String[] reservAndUser=line.split("  ") ;
                //Splitting the line into the identified e.g GAME and the userdefined parameters.
                String identifier=reservAndUser[0];
                String parameters=reservAndUser[1];
                
                System.out.println("the indentifier is : " + identifier);
                System.out.println("the parameters are : " + parameters);

                //Determinig the course of action based on the Command/Identifier.
                switch(identifier){
                    case "PLAYER":
                        //The player name will be the second word after splitting by :
                        String playerName=parameters.split(":",0)[1];
                        System.out.println("player name is "+playerName);
                        //Adding the player to the players table
                        players.put(playerName,new Player(playerName,null));
                        break;

                    case "STRATEGY":
                        String strategyName=parameters.split(":",0)[1];
                        System.out.println("Strategy name is "+strategyName);
                        String movesStr=parameters.split(":",0)[2];
                        movesStr=movesStr.substring(1,movesStr.length()-1);
                        //movesList holds the list of moves in a strategy.
                        ArrayList<String> movesList = new ArrayList<String>(Arrays.asList(movesStr.split(",",0)));
                        System.out.println("Strategy list is "+movesList.toString());
                        //Adding the strategy to the strategies table.
                        strategies.put(strategyName,movesList);
                        break;

                    case "OUTCOMES":
                        String outcomesName=parameters.split(":",0)[1];
                        System.out.println("outcomes name is "+outcomesName);
                        String outcomesStr=parameters.split(":",0)[2];

                        //Testing the converter from String to ArrayList<ArrayList<Pair>>.
                        OutcomesConverter converter = new OutcomesConverter();

                        //outcomeslist is the result of converting outcomesStr to an Array<Array<Pair>>.
                        ArrayList<ArrayList<Pair>> outcomeslist = converter.convertToArrayOfPairs(outcomesStr);

                        //Placing it in the outcomes table.
                        outcomes.put(outcomesName,outcomeslist);

                        //Printing with formatting
                        writer.write(outcomeslist.size()+"\n");
                        writer.write(outcomeslist.get(0).size()+"\n");

                        //Iterating over the pairs and writing into the file.
                        for (ArrayList<Pair> array : outcomeslist){
                            for(Pair pair: array){
                                writer.write(pair.getElement(0)+" "+pair.getElement(1)+"\n");
                            }
                        }

                        break;

                    case "GAME":
                        //The game name will be the second word after splitting by :
                        String gameName=parameters.split(":",0)[1];
                        System.out.println("game name is "+gameName);

                        //Adding the game to the games table
                        Game gimmo = new Game(gameName,null, null, null);
                        System.out.println("here is the name of the gamre: " + gimmo.getName());
                        games.put(gameName,gimmo);
                        System.out.println("game registered");
                        Game gimmi = games.get(gameName);
                        System.out.println("here is the name of the gamre form the has table: " + gimmi.getName());
                        break;

                    case "ASSOCIATE":
                        String playerAssociate=parameters.split(":",0)[1];
                        String strategyAssociate=parameters.split(":",0)[2];
                        System.out.println("player and strategy are"+playerAssociate+strategyAssociate);

                        //Associating a player with a strategy.
                        players.get(playerAssociate.trim()).setStrategies(strategies.get(strategyAssociate));

                        break;

                    case "LINK":
                        //the number of parameters will determine the nature of the parameters. Either 2 players and a game or a game and an outcomes matrix.
                        if(parameters.split(":",0).length==3){
                            System.out.println("in the if ");
                            //Linking a game and an outcomes matrix
                            String gameLink=parameters.split(":",0)[1];
                            String outcomesLink=parameters.split(":",0)[2];

                            games.get(gameLink).setOutcomes(outcomes.get(outcomesLink));

                        }else if(parameters.split(":",0).length==4){
                            System.out.println("in the else ");
                            //Linking a game with 2 players
                            String gameLink=parameters.split(":",0)[1];
                            String playerLink1=parameters.split(":",0)[2];
                            String playerLink2=parameters.split(":",0)[3];
                            System.out.println("cool till now");
                            System.out.println("game is " + games.get(gameLink).getName());
                            
                            games.get(gameLink).setPlayers(new Player[]{players.get(playerLink1),players.get(playerLink2)});

                        }
                        break;

                    case "NASH":
                        //The game we want to find the Nash Equilibriumm of.
                        String gameNash=parameters.split(":",0)[1];
                        System.out.println("NASH GANE IS "+gameNash);
                        //Printing the result .Should be written to FILE
                        //UNCOMMENT WHEN OUTCOMES IS READY.
                        try {
                            System.out.println(games.get(gameNash).nash());
                        }catch(NashNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                        //Writing to the file with formatting.
                        String strNash = "";
                        try{
                            // u just need to copy the thing in here and then just copy paste this code
                            writer.write("nash: ");
                            ArrayList<Pair> nash = games.get(gameNash).nash();
                            for(Pair p : nash){
                                strNash += ("(" + (games.get(gameNash).getPlayers())[0].getStrategies().get(p.getElement(0)) + "," +  (games.get(gameNash).getPlayers())[1].getStrategies().get(p.getElement(1)) + ")") + " ";
                            }
                            System.out.println("here is the nash string: " + strNash);
                            writer.write(strNash);
                            writer.write("\n");
                        }catch(NashNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "PARETO":
                        //The game we want to find the PARETO value.
                        String gamePareto=parameters.split(":",0)[1];
                        System.out.println("PARETO GAME IS "+gamePareto);
                        //Printing the result .Should be written to FILE

                        System.out.println(games.get(gamePareto).pareto());

                        //writing with formatting.
                        String strPareto = "";
                        writer.write("pareto: ");
                        for(Pair p : games.get(gamePareto).pareto()){
                            strPareto += ("(" + (p.getElement(0)) + "," +  (p.getElement(1)) + ")") + " ";
                        }
                        writer.write(strPareto+"\n");

                        break;

                    case "BEST_RES":
                        /*String gameBest=parameters.split(":",0)[1];
                        String playerBest1=parameters.split(":",0)[2];
                        String playerBest2=parameters.split(":",0)[3];
                        String moveBest=parameters.split(":",0)[4];*/
                        
                        String gameBest="game1";
                        String playerBest1=parameters.split(":",0)[1];
                        System.out.println("the player 1 is " + playerBest1);
                        String playerBest2=parameters.split(":",0)[2];
                        System.out.println("the player 2 is " + playerBest2);
                        String moveBest=parameters.split(":",0)[3];
                        System.out.println("the move best  is " + moveBest);

                        System.out.println("problem not with split statements may be");
                        if(players.get(playerBest1).equals(games.get(gameBest).getPlayers()[0])){
                            System.out.println("helo");
                            //System.out.println(games.get(gameBest).best_response(0,1,moveBest));
                        }else{
                           // System.out.println(games.get(gameBest).best_response(1,0,moveBest));
                        }

                        //printing with formatting.
                        String strBest = "";
                        System.out.println("Cool till now for best res");
                        writer.write("best response : ");
                        String gimme = games.get(gameBest).getName();
                        System.out.println("here is the name of the game we are working with in best : " + gameBest);
                        ArrayList<Integer> best = (games.get(gameBest)).best_response(0, 1, moveBest);
                        System.out.println("the array seems fine");
                        for(int i: best){
                            // the zero in the following is because it is the player zero that looks for the best response
                            strBest += games.get(gameBest).getPlayers()[0].getStrategies().get(i) + " ";
                        }

                        writer.write(strBest);
                        System.out.println("finally");
                        writer.write("\n");

                        break;

                }
                System.out.println("Ready to take a new line");

                //New line
                line = reader.readLine();
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}