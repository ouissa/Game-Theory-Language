/*"
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lang_classes;

/**
 *
 * @author supernova
 */
public class Pair {
    
    private int pl1_outcome;
    private int pl2_outcome;

    public Pair(int pl1_outcome, int pl2_outcome) {
        this.pl1_outcome = pl1_outcome;
        this.pl2_outcome = pl2_outcome;
    }
    
    public void setElement(int index, int payoff){
        if(index == 0)
            pl1_outcome = payoff;
        else
            pl2_outcome = payoff;
    }
    
    public int getElement(int index){
        if(index == 0)
            return pl1_outcome;
        else
            return pl2_outcome;
    }
    
    
    public String toString(){
        return "first : " + pl1_outcome + ", second : " + pl2_outcome + "\n"; 
    }
}
