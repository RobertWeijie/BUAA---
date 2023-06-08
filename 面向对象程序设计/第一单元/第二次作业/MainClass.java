//import com.oocourse.spec2.ExprInput;
//import com.oocourse.spec2.ExprInputMode;
import expr.Diyfunction;
import expr.Expr;
import expr.Factor;
import expr.Totaldiyfunctions;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
//        ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);
//        int n = scanner.getCount();
        Scanner scanner =  new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Totaldiyfunctions totaldiyfunctions = new Totaldiyfunctions();
        for (int i = 0; i < n; i++) {
            String diyinput = scanner.nextLine();
//            String diyinput = scanner.readLine();
            Lexer functionlexer = new Lexer(diyinput);
            Parser functionparser = new Parser(functionlexer);
            Factor diyfunction;
            diyfunction = functionparser.diyfactor(functionlexer.peek());
            if (functionlexer.peek().equals(" ")) {
                functionlexer.next();
            }
            functionlexer.next();
            if (functionlexer.peek().equals(" ")) {
                functionlexer.next();
            }
            Expr content = functionparser.parseExpr();
            if (diyfunction instanceof Diyfunction) {
                ((Diyfunction) diyfunction).setContent(content);
                totaldiyfunctions.addFunction((Diyfunction) diyfunction);
            }
        }
//        String input = scanner.readLine();
        String input = scanner.nextLine();
        Lexer lexer = new Lexer(input);
        lexer.setTotaldiyfunctions(totaldiyfunctions);
        Parser parser = new Parser(lexer);
        //aa
        Expr expr = parser.parseExpr();
        expr = expr.addsimplify();
        System.out.println(expr);
    }
}