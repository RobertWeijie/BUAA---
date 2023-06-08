package HW3.expr;

import java.math.BigInteger;

public class Exprpow implements Factor {
    private final Factor factor;
    private int exponent;

    public Exprpow(Factor factor, int exponent) {
        this.factor = factor;
        this.exponent = exponent;
    }

    public Factor getFactor() {
        return factor;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public Factor expand() {
        Expr expr = new Expr();
        Term term = new Term();
        expr.addTerm(term);
        for (int i = 0; i < exponent; i++) {
            term.addFactor(factor);
        }
        term.setSimplified(false);
        return expr;
    }

    @Override
    public Exprpow copy() {
        return new Exprpow(this.getFactor().copy(), this.getExponent());
    }

    @Override
    public Factor getRadix() {
        return null;
    }

    @Override
    public int getExp() {
        return exponent;
    }

    @Override
    public Expr derivate(String name) {
        Expr expr = new Expr();
        Term term = new Term();
        Exprpow exprpowCopy = this.copy();
//        term.setCoefficient(new BigInteger(exponent+""));
        term.addFactor(new Number(new BigInteger(exponent+"")));
        exprpowCopy.setExponent(exprpowCopy.getExp()-1);
        term.addFactor(exprpowCopy);
        term.addFactor(factor.derivate(name));
        expr.addTerm(term);
        Term term1 = new Term();
        term1.addFactor(factor.derivate(name));
        expr.addTerm(term1);
        return expr;
    }
}
