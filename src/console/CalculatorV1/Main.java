package console.CalculatorV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){

        System.out.println(
                " _____________________ \n"  +
                        "|  _________________  |\n"  +
                        "| |                 | |\n"  +
                        "| |_________________| |\n"  +
                        "|   ___ ___ ___ ___   |\n"  +
                        "|  | 1 | 2 | 3 | 4 |  |\n"  +
                        "|  |___|___|___|___|  |\n"  +
                        "|  | 5 | 6 | 7 | 8 |  |\n"  +
                        "|  |___|___|___|___|  |\n"  +
                        "|  | 9 | 0 | + | - |  |\n"  +
                        "|  |___|___|___|___|  |\n"  +
                        "|  | x | / | x^| √ |  |\n"  +
                        "|  |___|___|___|___|  |\n"  +
                        "|  |1/x|Cos|Sin|Tan|  |\n"  +
                        "|  |___|___|___|___|  |\n"  +
                        "|  | x!|log| π |In |  |\n"  +
                        "|  |___|___|___|___|  |\n"  +
                        "|_____________________|\n"
        );
        //자연 로그 ln()     :Math.log();
        //상용 로그 log10(): Math.log10();
        //
        System.out.println("Type 'help' for help, and 'exit' to exit. Otherwise input a math problem.");
        Main p = new Main();
        p.input();
    }

    String[] operands = {"Tan","Cos", "Sin"};
    String[] split;

    public void input(){

        Double ans = 0.0;
        while(true){

            String str;
            System.out.print("> ");
            Scanner scan = new Scanner(System.in);
            str = scan.nextLine();

            String pattern1 = "^\\w{3}";
            String pattern2 = "\\(?\\d+\\.?\\d*\\)?";

            List<String> withParenthesis = new ArrayList<String>(); //tan
            List<String> returned = new ArrayList<String>();  //number

            Matcher m1 = Pattern.compile(pattern1).matcher(str);

            getAngleFunctions(str, pattern2, withParenthesis, returned, m1);

            System.out.println("m1 = " + withParenthesis);
            System.out.println("m2 = " + returned);

            System.out.println(doMath(Double.parseDouble(returned.get(0)), withParenthesis.get(0)));
        }
    }

    private void getAngleFunctions(String str, String pattern2, List<String> withParenthesis, List<String> returned, Matcher m1) {
        while (m1.find()) {
            withParenthesis.add(m1.group());
        }

        Matcher m2 = Pattern.compile(pattern2).matcher(str);
        while (m2.find()) {
            returned.add(m2.group());
        }
    }


    public Double doMath(double arg1, String compare){
        Double answer = 0.0;
        switch (compare){
            case "Cos": answer = cos(arg1); break;
            case "Tan": answer = tan(arg1); break;
            case "Sin": answer = sin(arg1); break;
        }
        return answer;
    }

    //cos
    public static double cos(double n) {
        return Math.cos(Math.toRadians(n));
    }

    //sin
    public static double sin(double n) {
        return Math.sin(Math.toRadians(n));
    }

    //tan
    public static double tan(double n) {
        return Math.tan(Math.toRadians(n));
    }
}