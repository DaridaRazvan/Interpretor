package Model.Value;

import Model.Types.*;

public class BoolValue implements Value{
    Boolean value;

    public BoolValue(boolean value){
        this.value = value;
    }
    public BoolValue(){
        this.value = false;
    }

    public boolean getValue(){
        return this.value;
    }
    public Type getType(){
        return new BoolType();
    }

    public String toString(){
        return Boolean.toString(value);
    }

    public boolean equals(Object object){
        if(this == object)
            return true;
        if(!(object instanceof BoolValue))
            return false;
        BoolValue objectBoolean = (BoolValue) object;
        return value.equals(objectBoolean.value);
    }
}
