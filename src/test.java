
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String s = getNameInput();
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
            input = sc.nextLine();
            for(String option : inputOptions){
                isValidInput=option.equals(input);
            }
            if(!isValidInput) System.out.println("Invalid option!!");
        }
        return input;
    }
    public static String getNameInput(){
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        while (!Pattern.matches("[a-zA-z]+[a-zA-Z0-9_\\s]*", string)){
            System.out.println("Invalid Input! Should start with Alphabet.In special characters only _ is allowed.");
            string = sc.nextLine();
        }
        return string;
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

