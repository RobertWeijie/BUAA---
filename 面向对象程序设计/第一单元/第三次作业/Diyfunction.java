package HW3.expr;


import java.util.ArrayList;

public class Diyfunction implements Function {
    private String name;
    private ArrayList<Factor> arguments;
    private Expr content;

    public Diyfunction(String name) {
        this.name = name;
        this.arguments = new ArrayList<>(3);
    }

    public void addArgument(Factor argument) {
        this.arguments.add(argument);
    }

    public void setContent(Expr content) {
        this.content = content;
    }

    public Expr simplify(Totaldiyfunctions totaldiyfunctions) {
        for (Diyfunction item : totaldiyfunctions.getDiyfunctions()) {
            if (item.name.equals(this.name)) {
                this.setContent(item.content.copy());
                Factor fx = null,fy = null ,fz = null;
                for (int i = 0; i < arguments.size(); i++) {
                    if (((Pow) item.arguments.get(i)).getName().equals("x")) {
                        fx = this.arguments.get(i);
                        this.content.substitute("x", new Pow("U", 1));
                    }
                }
                for (int i = 0; i < arguments.size(); i++) {
                    if (((Pow) item.arguments.get(i)).getName().equals("y")) {
                        fy = this.arguments.get(i);
                        this.content.substitute("y", new Pow("V", 1));
                    }
                }
                for (int i = 0; i < arguments.size(); i++) {
                    if (((Pow) item.arguments.get(i)).getName().equals("z")) {
                        fz = this.arguments.get(i);
                        this.content.substitute("z", new Pow("W", 1));

                    }
                }
                if(fx != null) {
                    this.content.substitute("U", fx);
                }
                if(fy != null) {
                    this.content.substitute("V", fy);
                }
                if(fz != null) {
                    this.content.substitute("W", fz);
                }
                return this.content.addsimplify();
            }
        }
        return null;
    }

    public Diyfunction copy() {
        Diyfunction newdiyfunction = new Diyfunction(this.name);
        newdiyfunction.setContent(this.content.copy());
        for (Factor item : this.arguments) {
            newdiyfunction.arguments.add(item.copy());
        }
        return newdiyfunction;
    }

    @Override
    public Factor getRadix() {
        return null;
    }

    @Override
    public int getExp() {
        return 0;
    }

    @Override
    public Factor derivate(String name) {
        return null;
    }
}
