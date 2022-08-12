package console.CalculatorV1;

import java.util.*;

public class StackCalculatorWithParentheses {
    private static final double PI = 3.141592;

    public static void main(String[] args) {



        final String[] operands = {
                "Tan","Cos", "Sin",
                "√", "log", "ln",
                "!", "^", "π"};

        boolean run = true;
        Scanner sc = new Scanner(System.in);

        System.out.println("어서오세요. 공학계산 프로그램입니다.");
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
                "|  | * | / | x^| √ |  |\n"  +
                "|  |___|___|___|___|  |\n"  +
                "|  | x!|Cos|Sin|Tan|  |\n"  +
                "|  |___|___|___|___|  |\n"  +
                "|  | π |log| In|종료|  |\n"  +
                "|  |___|___|___|___|  |\n"  +
                "|_____________________|\n"
                          );


        while (run) {
            System.out.println("---------------------------------------------------");
            System.out.println("1. 계산하러 가기 | 2. 종료");
            System.out.println("---------------------------------------------------");
            System.out.println("선택> ");

            int menuNum = Integer.parseInt(sc.nextLine());


            switch (menuNum) {
                case 1:
                    System.out.println("입력은 한칸 씩 띄워서 작성해주세요.");
                    System.out.println("(예시) : 10 + ( √25 + 3! - 4 + Sin10 ) + ln20 + Cos10 + log10 처럼 입력해주세요.");
                    System.out.println("계산하고자 하는 문장을 입력해주세요> ");
                    String expression = sc.nextLine();
                    String[] operandCalculatorV2 = getOperandCalculatorV2(operands, expression);
                    String stackExpressionStr[] = divideExpression(operandCalculatorV2);
                    System.out.println("----------------------계산 과정-----------------------------");
                    System.out.println("\n정답은 = " + calculate(stackExpressionStr));
                    break;

                case 2:
                    run = false;
                    System.out.println("종료 키가 입력되어 종료합니다.");
                    break;

            }
        }

    }

    private static String[] getOperandCalculatorV2(String[] operands, String expression) {
        //String tmp = "";
        HashMap<String, String> map = new HashMap<>();
        HashMap<Integer, String> storage = new HashMap<>();
        String[] expressionArray = expression.split(" ");
        String[] returnStorage = new String[expressionArray.length];
        for (int i = 0; i < expressionArray.length; i++) {
            //System.out.println("i = " + i + ", value = " + expressionArray[i]);
            storage.put(i, expressionArray[i]);

            IsContainOperand(operands, map, storage, expressionArray, i);
        }

        return getOperandResult(storage, returnStorage);
    }

    private static String[] getOperandResult(HashMap<Integer, String> storage, String[] returnStorage) {
        for (Integer integer : storage.keySet()) {
            String s = storage.get(integer);
            returnStorage[integer] = String.format(s);
        }
        return returnStorage;
    }

    private static void IsContainOperand(String[] operands, HashMap<String, String> map, HashMap<Integer, String> storage, String[] expressionArray, int i) {
        String tmp;
        Double ans = 0.0d;
        for (String operand : operands) {
            if (expressionArray[i].contains(operand)) {
                ans = ExtractNumber(expressionArray, i, ans, operand);

                tmp = Double.toString(ans);
                map.put(expressionArray[i], tmp);
                storage.put(i, tmp);
            }
        }
    }

    private static Double ExtractNumber(String[] expressionArray, int i, Double ans, String operand) {
        if (expressionArray[i].startsWith(operand)) {
            Double number = Double.parseDouble(expressionArray[i].substring(operand.length()));
            ans = doMath(operand, number);
        }

        else if(expressionArray[i].endsWith(operand)) {
            Double number = Double.parseDouble(expressionArray[i].substring(0, expressionArray[i].length() - operand.length()));
            ans = doMath(operand, number);
        }
        return ans;
    }


    private static String[] divideExpression(String[] expression) {
        String expressionStr = "";
        Stack<String> operatorStack = new Stack<String>();

        for (String s : expression) {
            try {
                double number = Double.parseDouble(s);
                expressionStr += number + " ";
            } catch (NumberFormatException e) {
                if (s.equals("(")) operatorStack.push("(");
                else if (s.equals(")")) {
                    while (!operatorStack.peek().equals("(")) expressionStr += operatorStack.pop() + " ";
                    operatorStack.pop();
                } else {
                    OperatorPriorityWithParentheses priority = OperatorPriorityWithParentheses.findPriority(s);
                    while (!operatorStack.isEmpty()) {
                        String expInStack = operatorStack.peek();
                        if (priority.getPriority() <= OperatorPriorityWithParentheses.findPriority(expInStack).getPriority()) {
                            expressionStr += operatorStack.pop() + " ";
                        } else {
                            break;
                        }
                    }
                    operatorStack.push(s);
                }
            }
        }
        while (!operatorStack.isEmpty()) expressionStr += operatorStack.pop() + " ";
        return expressionStr.trim().split(" ");
    }

    private static double calculate(String[] stackExpressionStr) {
        Stack<Double> numberStack = new Stack<Double>();
        for (String s : stackExpressionStr) {
            try {
                double number = Double.parseDouble(s);
                numberStack.push(number);
            } catch (NumberFormatException e) {
                double number1 = numberStack.pop();
                double number2 = numberStack.pop();
                System.out.print("\n" + number2 + s + number1);
                switch (s) {
                    case "+" :
                        numberStack.push(number2 + number1);
                        break;
                    case "-" :
                        numberStack.push(number2 - number1);
                        break;
                    case "*" :
                        numberStack.push(number2 * number1);
                        break;
                    case "/" :
                        numberStack.push(number2 / number1);
                        break;
                }
            }
        }
        return numberStack.pop();
    }

    public static Double doMath(String compare, double arg1){
        Double answer = 0.0;
        switch (compare){
            case "Cos": answer = cos(arg1); break;
            case "Tan": answer = tan(arg1); break;
            case "Sin": answer = sin(arg1); break;
            case "!": answer = factorial(arg1); break;
            case "√": answer = root(arg1); break;
            case "log": answer = log(arg1); break;
            case "ln": answer = ln(arg1); break;
            case "^": answer = pow(arg1); break;
            case "π": answer = pi(arg1); break;
        }
        return answer;
    }

    public static double pi(double n) {
        return PI*n;
    }
    public static double pow(double n) {
        return Math.pow(n,2);
    }

    //x!
    public static double factorial(double n) {
        if (n == 1) {
            return 1;
        } else {
            return n*factorial(n-1);
        }
    }

    //log()
    public static double log(double n) {
        return Math.log10(n);
    }

    //ln()
    public static double ln(double n) {
        return Math.log(n);
    }

    //root
    public static double root(double n) {
        return Math.sqrt(n);
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