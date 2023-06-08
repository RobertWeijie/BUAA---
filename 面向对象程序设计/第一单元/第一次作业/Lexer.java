package HW1;

public class Lexer {
    private final String input;
    private int pos = 0;
    private String curToken;

    public Lexer(String input) {
        this.input = input;
        this.next();
    }

    public void next() {
        if (pos == input.length()) {
            curToken = "END";
            return;
        }

        char c = input.charAt(pos);
        if (c == ' ' || c == '\t') {
            curToken = getWhite();
        } else if (Character.isDigit(c)) {
            curToken = getNumber();
        } else if ("()+-xyzfghi,=".indexOf(c) != -1) {
            pos += 1;
            curToken = String.valueOf(c);
        } else if (c == '*') {
            curToken = getMult();
        } else if (c == 's' || c == 'c') {
            curToken = getFunction();
        } else if (c == 'd') {
            curToken = getDerivate();
        }
    }

    private String getDerivate() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && input.charAt(pos) == 'd') {
            sb.append(input.charAt(pos));
            ++pos;
        }
        return sb.toString();
    }

    private String getNumber() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            ++pos;
        }
        return sb.toString();
    }

    private String getWhite() {
        while (pos < input.length() &&
                (input.charAt(pos) == ' ' ||
                        input.charAt(pos) == '\t')) {
            pos++;
        }
        next();
        return peek();
    }

    private String getMult() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && input.charAt(pos) == '*') {
            sb.append(input.charAt(pos));
            ++pos;
        }
        return sb.toString();
    }

    private String getFunction() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && "cosinum".indexOf(input.charAt(pos)) != -1) {
            sb.append(input.charAt(pos));
            ++pos;
        }
        return sb.toString();
    }

    public String peek() {
        return this.curToken;
    }
}
