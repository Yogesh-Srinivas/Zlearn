
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        String s = getOptions("a","b","A","c");
        System.out.println(s);
    }
    public static String getMultiLineInput(){
        String finalString="";
        while (finalString.equals("")) {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                if (input.equals("")) break;
                finalString += input + "\n";
            }
        }
        System.out.println(finalString);
        return finalString;
    }

    public static String getOptions(String... inputOptions) {
        Scanner sc = new Scanner(System.in);
        String input="";
        boolean isValidInput = false;
        while(!isValidInput){
            input = sc.next();
            for(String option : inputOptions){
                isValidInput=option.equals(input);
            }
            if(!isValidInput) System.out.println("Invalid option!!");
        }
        return input;
    }
}

class A{
    void use(){
        System.out.println("use a");
    }
}

class B extends A{
    @Override
    void use(){
        System.out.println("use b");
    }
}

