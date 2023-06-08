package HW3.expr;

public interface Factor {

    Factor copy();

    Factor getRadix();

    int getExp();

    Factor derivate(String name);
}
