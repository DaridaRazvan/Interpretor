package Model.Value;

import Model.Types.RefType;
import Model.Types.Type;

import java.util.concurrent.atomic.AtomicInteger;

public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int address, Type locationType){
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddr() {
        return address;
    }

    public Type getType() {
        return new RefType(locationType);
    }

    public String toString(){
        return "(" + address + ", " + locationType + ")";
    }
}
