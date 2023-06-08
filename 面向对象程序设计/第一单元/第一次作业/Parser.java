package HW1;

import HW1.expr.Number;
import HW1.expr.*;

import java.math.BigInteger;

public class Parser {
    private final Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public Expr parseExpr() {
        boolean negative = false;
        if (lexer.peek().equals("+")) {
            lexer.next();
        } else if (lexer.peek().equals("-")) {
            negative = true;
            lexer.next();
        }
        Term term = parseTerm();
        if (negative) {
            term.addFactor(new Number(BigInteger.valueOf(-1)));
        }
        Expr expr = new Expr();
        expr.addTerm(term);
        while (true) {
            boolean sub = false;
            if (lexer.peek().equals("+")) {
                lexer.next();
            } else if (lexer.peek().equals("-")) {
                sub = true;
                lexer.next();
            } else {
                break;
            }
            term = parseTerm();
            if (sub) {
                term.addFactor(new Number(BigInteger.valueOf(-1)));
            }
            expr.addTerm(term);
        }
        return expr;
    }

    public Term parseTerm() {
        Term term = new Term();
        if (lexer.peek().equals("+")) {
            lexer.next();
        } else if (lexer.peek().equals("-")) {
            term.addFactor(new Number(BigInteger.valueOf(-1)));
            lexer.next();
        }
        Factor factor = parseFactor();
        if (factor instanceof Exprpow) {
            for (int i = ((Exprpow) factor).getExponent(); i > 0; i--) {
                term.addFactor(((Exprpow) factor).getFactor());
            }
            term.setSimplified(false);
        } else {
            if (factor instanceof Expr) {
                term.setSimplified(false);
            }
            term.addFactor(factor);
        }
        while (lexer.peek().equals("*")) {
            lexer.next();
            factor = parseFactor();
            if (factor instanceof Exprpow) {
                for (int i = ((Exprpow) factor).getExponent(); i > 0; i--) {
                    term.addFactor(((Exprpow) factor).getFactor());
                }
                term.setSimplified(false);
            } else {
                if (factor instanceof Expr) {
                    term.setSimplified(false);
                }
                term.addFactor(factor);
            }
        }
        return term;
    }

    public Factor parseFactor() {
        if (lexer.peek().equals("(")) {
            return exprfactor();
        } else if (lexer.peek().equals("x") ||
                lexer.peek().equals("y") ||
                lexer.peek().equals("z") ||
                lexer.peek().equals("i")) {
            return powfactor(lexer.peek());
        } else {
            return numfactor();
        }
    }

    public Factor exprfactor() {
        lexer.next();
        Factor expr = parseExpr();
        //if (!lexer.peek().equals(")")){Wrong Format!}
        lexer.next();
        if (lexer.peek().equals("**")) {
            lexer.next();
            if (lexer.peek().equals("+")) {
                lexer.next();
            }
            int i = Integer.parseInt(lexer.peek());
            if (i == 0) {
                lexer.next();
                return new Number(BigInteger.valueOf(1));
            } else if (i == 1) {
                lexer.next();
                return expr;
            } else {
                lexer.next();
                return new Exprpow(expr, i);
            }
        } else {
            return ((Expr) expr).addsimplify();
        }
    }

    public Factor powfactor(String name) {
        lexer.next();
        if (lexer.peek().equals("**")) {
            lexer.next();
            if (lexer.peek().equals("+")) {
                lexer.next();
            }
            int i = Integer.parseInt(lexer.peek());
            lexer.next();
            if (i == 0) {
                return new Number(BigInteger.valueOf(1));
            } else {
                return new Pow(name, i);
            }
        } else {
            return new Pow(name, 1);
        }
    }

    public Factor numfactor() {
        if (lexer.peek().equals("+")) {
            lexer.next();
            BigInteger num = new BigInteger(lexer.peek());
            lexer.next();
            return new Number(num);
        } else if (lexer.peek().equals("-")) {
            lexer.next();
            BigInteger num = new BigInteger(lexer.peek());
            lexer.next();
            return new Number(num.negate());
        } else {
            BigInteger num = new BigInteger(lexer.peek());
            lexer.next();
            return new Number(num);
        }
    }

    //    public static void main(String[] args) {
    //        Expr expr = new Expr();
    //        Term term = new Term();
    //        term.addFactor(new Number(new BigInteger("2")));
    //        term.addFactor(new Pow("x", 1));
    //        expr.addTerm(term);
    //        System.out.println(expr.addsimplify());
    //    }
}