package parser;

public class Parser {

    public void Parser(){}

    public boolean parseLine(String line){

        return expr(line);

    }

    public boolean expr(String line){
        String[] words = line.split(" ",0);
        if(dt(words[0].trim())){
            if(words.length==2) {
                return userdefined(words[1]);
            }else if(words.length>2){
                return userdefined(words[1]) && userdefined(words[2]);
            }

        }else if(verb(words[0].trim())){
            if(words.length==2) {
                return userdefined(words[1]);
            }else if(words.length==3){
                return userdefined(words[1]) && userdefined(words[2]);
            }else if(words.length>3){
                return userdefined(words[1]) && userdefined(words[2]) && userdefined(words[3]);
            }
        }
        return false;
    }

    public boolean dt(String datatype){

        if(datatype.equalsIgnoreCase("game") || datatype.equalsIgnoreCase("strategy") || datatype.equalsIgnoreCase("outcomes")  || datatype.equalsIgnoreCase("player") ){
            return true;
        }
        return false;
    }

    public boolean verb(String verb){

        if(verb.equalsIgnoreCase("nash") || verb.equalsIgnoreCase("associate") || verb.equalsIgnoreCase("link")  || verb.equalsIgnoreCase("pareto")  || verb.equalsIgnoreCase("best_res")  || verb.equalsIgnoreCase("dom_str") || verb.equalsIgnoreCase("display") ) {
            return true;
        }
        return false;
    }


    public boolean userdefined(String word){
            return (word(word) || word.matches("\\[(([a-zA-Z0-9 | _ ]+),)*([a-zA-Z0-9 | _]+)\\]") || word.matches("\\[((\\[(\\d+/(\\d)+,)*(\\d)+/(\\d)+\\]),)*(\\[(\\d+/(\\d)+,)*(\\d)+/(\\d)+\\])\\]"));
    }


    public boolean word(String word){
        return word.matches("[a-zA-Z0-9 | _]*");
    }



}
