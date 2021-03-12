package Model.Types;

import Model.Value.StringValue;
import Model.Value.Value;

public class StringType implements Type {

    public boolean equals(Object object){
        return object instanceof StringType;
    }

    public Value getDefault(){
        return new StringValue("");
    }

    public String toString(){
        return "string";
    }
}
