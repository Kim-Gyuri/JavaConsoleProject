package console.CalculatorV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){

        System.out.println(
                        " _____________________\n"  +
                        "|  _________________  |\n" +
                        "| |                 | |\n" +
                        "| |_________________| |\n" +
                        "|   ___ ___ ___ ___   |\n" +
                        "|  | 1 | 2 | 3 | 4 |  |\n"   +
                        "|  |___|___|___|___|  |\n" +
                        "|  | 5 | 6 | 7 | 8 |  |\n" +
                        "|  |___|___|___|___|  |\n" +
                        "|  | 9 | 0 | + | - |  |\n" +
                        "|  |___|___|___|___|  |\n" +
                        "|  | x | / | x^| √ |  |\n" +
                        "|  |___|___|___|___|  |\n" +
                        "|  |1/x|Cos|Sin|Tan|  |\n" +
                        "|  |___|___|___|___|  |\n" +
                        "|  | x!|log| π | = |  |\n" +
                        "|  |___|___|___|___|  |\n" +
                        "|_____________________|\n"
        );

        System.out.println("Type 'help' for help, and 'exit' to exit. Otherwise input a math problem.");
        Main p = new Main();
        p.input();
    }

    public void input(){
        Double ans = 0.0;
        while(true){
            String str;
            System.out.print("> ");
            Scanner scan = new Scanner(System.in);
            str = scan.nextLine();
            str = properFormatting(str, ans);
            if (str.matches("help")){
                System.out.println("Please type a math problem. Be sure to use '*' not 'x'. 'ans' is the previous answer.");
                continue;
            }
            if (str.matches("exit")){
                System.out.println("Program Ended");
                scan.close();
                break;
            }
            System.out.println(findNumbers(str) + " : " + findOperands(str));
            ans = parenthesisFinder(findNumbers(str), findOperands(str));
            System.out.println(ans);
        }
    }

    //STRING FORMATTTING

    public String properFormatting(String s, Double a){
        s = s.replaceAll(" ", "").toLowerCase();
        if (s.endsWith(".")){
            s = s.substring(0, s.length()-1);
        }
        s = s.replaceAll("ans", a.toString());
        s = s.replaceAll("!", "!1");
        s = s.replaceAll("π","π1");
        s = s.replaceAll("Cos", a.toString());
        List<String> tempStr = new ArrayList<>();
        Matcher m = Pattern.compile("[^\\d\\w)](?=[+ \\- * \\/ % ^])-\\d+\\.?\\d*").matcher(s);
        while (m.find()) {
            tempStr.add(m.group());
        }
        if (s.startsWith("-")){
            Matcher match = Pattern.compile("-\\d+\\.?\\d*").matcher(s);
            if (match.find()) {s = s.replaceFirst("-\\d+\\.?\\d*", "(0" + match.group(0) + ")");}

        }
        for (int y =0; y < tempStr.size(); y++){
            String rep1 = tempStr.get(y).substring(1,tempStr.get(y).length());
            String rep2 = tempStr.get(y).substring(0,1);
            s = s.replace(tempStr.get(y), rep2 + "(" + "0" + rep1+ ")");
        }
        boolean replaced = false;
        while (replaced == false){
            if (s.contains("))")){
                s = s.replaceAll("\\)\\)", "\\)+0\\)");
                continue;
            }
            if (s.contains("((")){
                s = s.replaceAll("\\(\\(", "\\(0+\\(");
                continue;
            }
            replaced = true;
        }
        return s;
    }

    //FIND NUMERS AND OPERANDS

    public List<String> findNumbers(String str){
        //Find the numbers that you need
        List<String> withParenthesis = new ArrayList<String>();
        Matcher m = Pattern.compile("\\(?\\d+\\.?\\d*\\)?").matcher(str);
        while (m.find()) {
            withParenthesis.add(m.group());
        }
        return withParenthesis;
    }

    public List<String> findOperands(String str){
        String[] operands = {"!","-","+","*","/","%","^","π","Cos"};
        String [] strArray = str.split("\\(?\\d+\\.?\\d*\\)?");
        List<String> returned = new ArrayList<String>();
        for (int x = 0; x < strArray.length; x++){
            for (int y = 0; y < operands.length; y++){
                if (strArray[x].contains(operands[y])){
                    returned.add(operands[y]);
                }
            }
        }
        return returned;
    }

    //DEAL WITH PARENTHESIS

    public Double parenthesisFinder(List<String> nums, List<String> opps){
        int start = -1;
        int stop = -1;
        int skips = 0;
        for (int i = 0; i < nums.size(); i++){
            if (nums.get(i).contains("(") && start == -1){
                nums.add(i, nums.get(i).replaceAll("\\(", ""));
                nums.remove(i+1);
                start = i;
            }
            if (nums.get(i).contains("(") && start != -1){
                skips++;
            }
            if (nums.get(i).contains(")") && skips == 0){
                stop = i;
                nums.add(i, nums.get(i).replaceAll("\\)", ""));
                nums.remove(i+1);
            } else if (nums.get(i).contains(")") && skips != 0) { skips--; }
        }
        if (start == -1 && stop == -1){
            //no parenthesis were found
            List<Double> numsAsDubs = new ArrayList<Double>();
            for (String s : nums) {
                numsAsDubs.add(Double.parseDouble(s));
            }
            return math(numsAsDubs,opps);
        } else {

            try{
                List<String> tempNumList = new ArrayList<>(nums.subList(start, stop+1));
                List<String> tempOppList = new ArrayList<>(opps.subList(start, stop));
            } catch(IllegalArgumentException e){
                System.out.println("Parentheis get lonely without a match. Make sure all of yours have one.");
                return 0.0;
            }finally{}

            List<String> tempNumList = new ArrayList<>(nums.subList(start, stop+1));
            List<String> tempOppList = new ArrayList<>(opps.subList(start, stop));

            for (int x = start; x < stop; x++){
                nums.remove(start);
                opps.remove(start);
            }
            nums.remove(start);

            Double replacement = parenthesisFinder(tempNumList,tempOppList);
            //System.out.println("replacement" + replacement);

            nums.add(start,replacement.toString());
            return parenthesisFinder(nums, opps);
        }
    }

    //DO MATH ON THE INDEXES OF THE LISTS THAT ARE PASSED

    public Double math(List<Double> numbers, List<String> operands){
        Double temp = 0.0;
        String[] orderOfOpps = {"!","^","%","*/","+-","π","Cos"};
        if (numbers.size() <= operands.size()){
            System.out.println("This isn't right");
            return 0.0;
        }
        int inOrder = 0;
        int k = 0;
        while (operands.size() > 0){
            if (orderOfOpps[inOrder].contains(operands.get(k))){
                temp = doMath(numbers.get(k),numbers.get(k+1),operands.get(k));
                numbers.remove(k);
                numbers.remove(k);
                numbers.add(k,temp);
                operands.remove(k);
            } else{k++;}
            if (k == operands.size()){
                k = 0;
                inOrder++;
            }
        }
        return numbers.get(0);
    }

    public Double doMath(Double arg1, Double arg2, String compare){
        Double answer = 0.0;
        switch (compare){
            case "!": answer = fact(arg1); break;
            case "^": answer = Math.pow(arg1,arg2); break;
            case "%": answer = arg1%arg2; break;
            case "/": answer = arg1/arg2; break;
            case "*": answer = arg1*arg2; break;
            case "+": answer = arg1+arg2; break;
            case "-": answer = arg1-arg2; break;
            case "π": answer = pi(arg1); break;
            case "Cos": answer = cos(arg1); break;
        }
        return answer;
    }

    //COMPUTE
    public static double fact(double n) {
        if (n<=1)
            return 1;
        else
            return n*fact(n-1);
    }

    //π
    public static double pi(double n) {
        return n*Math.PI;
    }

    //cos
    public static double cos(double n) {
        return Math.cos(n);
    }

    //sin
    public static double sin(double n) {
        return Math.sin(n);
    }

    //tan
    public static double tan(double n) {
        return Math.tan(n);
    }
}