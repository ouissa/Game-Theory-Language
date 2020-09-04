/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lang_classes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author supernova
 */
public class Game_test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NashNotFoundException {
        // TODO code application logic here
        ArrayList<String> str = new ArrayList<>();
        
        str.add("x");
        str.add("y");
        str.add("z");
        
        ArrayList<String> str2 = new ArrayList<>();
        
        str2.add("a");
        str2.add("b");
        str2.add("c");
        str2.add("d");
        
        Player pl1 = new Player("pl1", str2);
        Player pl2 = new Player("pl2", str);
        
        ArrayList<ArrayList<Pair>> outcomes = new ArrayList<ArrayList<Pair>>();
        
        Scanner sc = new Scanner(System.in);

        Pair [][] out = {
            {new Pair(1,2),new Pair(2,2),new Pair(5,1)},
            {new Pair(4,2),new Pair(3,5),new Pair(3,3)},
            {new Pair(5,2),new Pair(4,4),new Pair(7,0)},
            {new Pair(2,3),new Pair(0,4),new Pair(3,0)}
        };
        
        
        
       String problem = "[[1/5,1/6,1/9],[2/5,2/6,2/9]]";
       
        for(int i = 0; i < problem.length(); ++i){
            
            if(problem.charAt(i) == '[' || problem.charAt(i) == ']')
                continue;
            else{
                
                if(problem.substring(i - 2, i).equals("[[") || problem.charAt(i - 1) == ',' || problem.substring(i - 2, i).equals(",[")){
                    
                    String first = problem.substring(i,i+1);
                    i++;
                    while(problem.charAt(i) != '/'){
                        first += problem.substring(i, i+ 1);
                    }
                    i++;
                    String second = problem.substring(i,i+1);
                    i++;
                    while(problem.charAt(i) != ','){
                        first += problem.substring(i, i+ 1);
                    }
                    int f = Integer.valueOf(first);
                    int s = Integer.valueOf(second);
                   System.out.println("lowele : " + f + "tani : "+ s);
                }
            }
        }
        
       ArrayList<Pair> added = new ArrayList<>();

       
       for(int i = 0; i < 4; ++i){
            ArrayList<Pair> row = new ArrayList<>();
            for(int j = 0 ; j < 3; ++j){
              
                row.add(out[i][j]);      
               
            }
            outcomes.add(row);
  
       }    
 
        Game gm = new Game("myGame",pl1, pl2, outcomes );
        System.out.println(gm.pareto());
        System.out.println("best " + gm.best_response(0,1,"y"));
        
        
        //_______________________________________________________________________________________________________________________________________________________
        
         //printing pareto
         
         String strPareto = "";
         System.out.print("pareto: ");
         for(Pair p : gm.pareto()){
            strPareto += ("(" + (p.getElement(0)) + "," +  (p.getElement(1)) + ")") + " ";
        }
         System.out.println(strPareto);
         
        // this is to print the best response
        
        String strBest = "";
        ArrayList<Integer> best = gm.best_response(0, 1, "y");
        System.out.print("best response : ");
        for(int i: best){
            // the zero in the following is because it is the player zero that looks for the best response
            strBest += gm.getPlayers()[0].getStrategies().get(i) + " ";
        }

         System.out.println(strBest);
        //this is to print  the nash equilibrium in the file
        String strNash = "";
        try{
        // u just need to copy the thing in here and then just copy paste this code
        ArrayList<Pair> nash = gm.nash();
        System.out.print("nash: ");
        for(Pair p : nash){
            strNash += ("(" + (gm.getPlayers())[0].getStrategies().get(p.getElement(0)) + "," +  (gm.getPlayers())[1].getStrategies().get(p.getElement(1)) + ")") + " ";
        }
        
         System.out.println(strNash);
        }catch(NashNotFoundException e){
            System.out.println(e.getMessage());
        }
        
    

    }
    
}
