package Model.Types;

public class IntType implements Type{

    public boolean equals(Object object){
        if (object instanceof IntType)
            return true;
        else
            return false;
    }
    public String toString(){
        return "int";
    }
}
