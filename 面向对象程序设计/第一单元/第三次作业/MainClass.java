package HW3;

import HW3.expr.Diyfunction;
import HW3.expr.Expr;
import HW3.expr.Factor;
import HW3.expr.Totaldiyfunctions;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Totaldiyfunctions totaldiyfunctions = new Totaldiyfunctions();
        for (int i = 0; i < n; i++) {
            String diyinput = scanner.nextLine();
            Lexer functionlexer = new Lexer(diyinput);
            functionlexer.setTotaldiyfunctions(totaldiyfunctions);
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
        String input = scanner.nextLine();
        if(input.split("d").length>2) {//求导算子只能出现一次
            System.out.println("Wrong Format");
            return;
        }
        Lexer lexer = new Lexer(input);
        lexer.setTotaldiyfunctions(totaldiyfunctions);
        Parser parser = new Parser(lexer);
        Expr expr = parser.parseExpr();
        Expr sintocos = (expr.copy()).sintocos();
        Expr costosin = (expr.copy()).costosin();
//        if (expr.toString().length() < sintocos.toString().length() &&
////                expr.toString().length() < costosin.toString().length()) {
////            System.out.println(expr);
////        } else if (sintocos.toString().length() < costosin.toString().length()) {
////            System.out.println(sintocos);
////        } else {
////            System.out.println(costosin);
////        }
        System.out.println(expr);
    }
}