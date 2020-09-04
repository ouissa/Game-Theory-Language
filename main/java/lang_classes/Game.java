/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lang_classes;

import java.util.ArrayList;
/**
 *
 * @author supernova
 */
public class Game {
    
    private String name; 
    private Player players[] = new Player[2];
    
    private ArrayList<ArrayList<Pair>> outcomes;

    public Game(String name, Player player1, Player player2, ArrayList<ArrayList<Pair>> outcomes) {
        this.name = name;
        this.players[0] = player1;
        this.players[1] = player2;
        this.outcomes = outcomes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player[] getPlayers(){
        return this.players;
    }

    public void setPlayers(Player[] players){
        this.players = players;
    }
    
    public ArrayList<ArrayList<Pair>> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(ArrayList<ArrayList<Pair>> outcomes) {
        this.outcomes = outcomes;
    }
    
    //Finding the pareto optimal outcome/s
    
    public ArrayList<Pair> pareto(){
    
        ArrayList<Pair> paretoList = new ArrayList<>();
        for(int i = 0; i < outcomes.size(); ++i){
            for(int j = 0 ; j < outcomes.get(0).size(); ++j){

                Pair current = new Pair(outcomes.get(i).get(j).getElement(0),outcomes.get(i).get(j).getElement(1));
                paretoList.add(current);
            }
        }
        
        for(int h = 0; h < paretoList.size(); ++h){
            myLoop:
            for(int i = 0; i < outcomes.size(); ++i){
                
                for(int j = 0 ; j < outcomes.get(0).size(); ++j){
                    
                    Pair current;
                    current = outcomes.get(i).get(j);
                    
                 
                    if(current.getElement(0) > paretoList.get(h).getElement(0) && current.getElement(1) > paretoList.get(h).getElement(1)){
                         paretoList.remove(h);
                         --h;
                         break myLoop;
                    }
                       
                }
            }
        
        }
        
        return paretoList;
    }
    
    // Finding the best response to a strategy played by the other player
    public ArrayList<Integer> best_response (int ans, int que, String st_played) {
     
        
        if(ans == que){
            
             return null;
            
        }else{
            
            int st_index;
            System.out.println("the player we are dealing with: " + players[que]);
            System.out.println("the strategies we are dealing with: " + players[que].getStrategies());
            st_index = players[que].getStrategies().indexOf(st_played);
            System.out.println("st_index is the fcking " + st_index);
            ArrayList<Pair> examined = new ArrayList<Pair>();
            
            if(que == 0){
                examined = outcomes.get(st_index);
                System.out.println(examined);
            }else{
                for(int i = 0; i < outcomes.size(); ++i){
                    examined.add(outcomes.get(i).get(st_index));
                } 
            }
            ArrayList<Integer> best_res = new ArrayList<>();
            int max_outcome = Integer.MIN_VALUE;

            for(int i = 0; i < examined.size(); ++i){

                int current = examined.get(i).getElement(ans);

                if(current >= examined.get(i).getElement(que)){

                    best_res.add(i);


                }
            }
            System.out.println("the length of best responses is: " + best_res);

            return best_res;
        }
        
       
    }
    
    //determine the nash equilibrium/equilibria  if there is any
    public ArrayList<Pair> nash()throws NashNotFoundException{
        
        ArrayList<ArrayList<Pair>> marked = new ArrayList<ArrayList<Pair>>();
          for(int i = 0; i < 4; ++i){
            ArrayList<Pair> row = new ArrayList<>();
            for(int j = 0 ; j < 3; ++j){
              
                row.add(new Pair(0,0));      
               
            }
            marked.add(row);
 
       }    
 
        ArrayList<Integer> best_res;
        ArrayList<Pair> nash_equi = new ArrayList<Pair>();
        
        for(int i = 0; i < outcomes.size(); ++i){
              
            best_res = best_response(1,0,players[0].getStrategies().get(i));
            System.out.println(best_res);
            for(int j = 0; j < best_res.size(); ++j){
                System.out.println("loopi");
                int index = best_res.get(j);
                marked.get(i).get(index).setElement(1,1);
            }
        }
        
        System.out.println("we are here");
        for(int i = 0; i < outcomes.get(0).size(); ++i){
            
            System.out.println(players[1].getStrategies().get(i));
            best_res = best_response(0,1,players[1].getStrategies().get(i));
            System.out.println(best_res);
             for(int j = 0; j < best_res.size(); ++j){
                 int index = best_res.get(j);
                marked.get(index).get(i).setElement(0,1);
            }
        }
        
        for(int i = 0; i < outcomes.size(); ++i){
            
        
            for(int j = 0; j < outcomes.get(0).size(); ++j){
                
                if(marked.get(i).get(j).getElement(0) == 1 && marked.get(i).get(j).getElement(1) == 1){
                    
                   
                    nash_equi.add(new Pair(i,j));
                    
                    
                }
                
            //there is a problem in here, it does not go to the second iteration
            
            }
        }
        
        if(nash_equi.isEmpty())
            throw new NashNotFoundException("no nash equilibrium found");
        else
            return nash_equi;
    }
    
    
    
    public String toString(){
        
        String returned = "";
        returned += name + " ";
      
        for(int i = 0; i <  outcomes.size(); ++i){
            for(int j = 0; j < outcomes.get(0).size() ; ++j){
               
                System.out.println("this pair: "+ outcomes.get(i).get(j).toString());
                returned += outcomes.get(i).get(j).toString() + "\n";
            }
            
            returned += "row finished";
        }
        
        return returned;
    }
    
}
