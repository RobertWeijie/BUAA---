package HW3.expr;

import java.math.BigInteger;
import java.util.Objects;

public class Pow implements Factor {
    private String name;
    private int exponent;

    public Pow(String name, int exponent) {
        this.name = name;
        this.exponent = exponent;
    }

    public String getName() {
        return name;
    }

    public int getExponent() {
        return exponent;
    }

    @Override
    public Pow copy() {
        return new Pow(this.getName(), this.getExponent());
    }

    @Override
    public Factor getRadix() {
        return new Pow(this.getName(), 1);
    }

    @Override
    public int getExp() {
        return exponent;
    }

    @Override
    public Factor derivate(String name) {
        if(!this.name.equals(name)) {
            return new Number(new BigInteger("0"));
        }
        if(exponent == 1) {
            return new Number(new BigInteger("1"));
        }
        Expr expr = new Expr();
        Term term = new Term();
        Pow copy = this.copy();
        copy.exponent = exponent - 1;
        term.addFactor(copy);
        term.addFactor(new Number(new BigInteger(""+exponent)));
        expr.addTerm(term);
        return expr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pow pow = (Pow) o;
        return exponent == pow.exponent && Objects.equals(name, pow.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, exponent);
    }
}
