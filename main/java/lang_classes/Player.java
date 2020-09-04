
package lang_classes;

import java.util.ArrayList;

/**
 *
 * @author supernova
 */
public class Player {
    
    private String name; 
    private ArrayList<String> strategies;

    public Player(String name, ArrayList<String> strategies) {
        this.name = name;
        this.strategies = strategies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getStrategies() {
        return strategies;
    }

    public void setStrategies(ArrayList<String> strategies) {
        this.strategies = strategies;
    }
  
    
}
