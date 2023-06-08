package HW1;

import HW1.expr.Expr;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        String input = scanner.nextLine();
        //        int count = 0;
        //        for (int i = 0; i < input.length(); i++) {
        //            if (input.charAt(i) == '(') {
        //                count++;
        //            }
        //        }
        //        if(count > 1) {
        //            System.out.println("Wrong Format!");
        //            return;
        //        }
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        Expr expr = parser.parseExpr();
        System.out.println(expr.addsimplify());
    }
}