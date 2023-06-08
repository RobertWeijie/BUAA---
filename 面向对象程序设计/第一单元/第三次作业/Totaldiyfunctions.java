package HW3.expr;

import java.util.ArrayList;

public class Totaldiyfunctions {
    private final ArrayList<Diyfunction> diyfunctions;

    public Totaldiyfunctions() {
        this.diyfunctions = new ArrayList<>(3);
    }

    public ArrayList<Diyfunction> getDiyfunctions() {
        return diyfunctions;
    }

    public void addFunction(Diyfunction diyfunction) {
        this.diyfunctions.add(diyfunction);
    }
}
