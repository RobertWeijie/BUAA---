package HW1.expr;

public interface Factor {

    Factor copy();

    Factor getRadix();

    int getExp();
}
