package Model.Types;

import Model.Value.Value;

public class BoolType implements Type {
    public boolean equals(Object object){
        if (object instanceof BoolType)
            return true;
        else
            return false;
    }
    public String toString(){
        return "boolean";
    }
}
