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
import java.util.ArrayList;
import lang_classes.Pair;

public class OutcomesConverter {
    //constructor
    public void OutcomesConverter(){}

    public ArrayList<ArrayList<Pair>> convertToArrayOfPairs(String problem) {

            ArrayList<ArrayList<Pair>> outcomes = new ArrayList<ArrayList<Pair>>();
            ArrayList<Pair> row = new ArrayList<Pair>();
            for(int i = 0; i < problem.length(); ++i){
                // check whether it is a brackeyt
            /* if(problem.charAt(i) == ',' && problem.charAt(i-1) == ']' && problem.charAt(i+1) == '['){

              row = new ArrayList<>();
            }*/
                if(problem.charAt(i) == '[' || problem.charAt(i) == ']')
                    continue;
                else{
                    // it can be a comma or a double bracket, or a beginning of a new row
                    if(problem.substring(i - 2, i).equals("[[") || problem.charAt(i - 1) == ',' || problem.substring(i - 2, i).equals(",[")){

                        //continuw scanning the left hand side until findinh "/"
                        String first = problem.substring(i,i+1);
                        i++;
                        while(problem.charAt(i) != '/'){

                            first += problem.substring(i, i+1);
                            i++;
                        }
                        i++;
                        //continuw scanning the right hand side until findinh "," if inside rray or "]" if the last elemnt of a row
                        String second = problem.substring(i,i+1);
                        i++;
                        while(problem.charAt(i) != ',' && problem.charAt(i) != ']'){
                            second += problem.substring(i, i+ 1);
                            i++;
                        }

                        //in case we have finished a row, we push the row, and we start obver again


                        //convert to integers
                        int f = Integer.valueOf(first);
                        int s = Integer.valueOf(second);

                        // here include the code where u use the pair (f,s)
                        //this one just prints the pair

                        row.add(new Pair(f,s));
                        if(problem.charAt(i) == ']'){
                            outcomes.add(row);
                            row = new ArrayList<>();

                        }

                    }
                    else
                        continue;
                }
            }
           return outcomes;
    }
}