package Utilities;

import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomScanner {
    public static int getIntegerInput(int minValue, int maxValue){
        int integerInput=-1;
        boolean isValidIntegerInput = false;
        while (!isValidIntegerInput){
            try{
                String input = new Scanner(System.in).nextLine();
                integerInput = Integer.parseInt(input);
                if(integerInput==0 && !input.equals("0")) throw new Exception();
                if(integerInput>maxValue || integerInput<minValue){
                    throw new Exception();
                }
                isValidIntegerInput = true;
            }catch (Exception e){
                if(minValue==maxValue) System.out.println("Invalid Input! Value must be "+minValue);
                else System.out.println("Invalid Input!! Value must be between "+minValue+" and "+maxValue+".");
            }
        }
        return integerInput;
    }
    public static String getMultiLineInput(){
        StringBuilder finalString= new StringBuilder();
        while (finalString.toString().equals("")) {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                if (input.equals("")) break;
                finalString.append(input).append("\n");
            }
        }
        return finalString.toString();
    }

    public static String getOptions(String... inputOptions) {
        Scanner sc = new Scanner(System.in);
        String input="";
        boolean isValidInput = false;
        while(!isValidInput){
            input = sc.nextLine();
            for(String option : inputOptions){
                if (option.equals(input)) {
                    isValidInput = true;
                    break;
                }
            }
            if(!isValidInput) System.out.println("Invalid option!!");
        }
        return input;
    }

    public static String getNameInput(){
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        while (!Pattern.matches("[a-zA-z]+[a-zA-Z0-9_\\s]*", string)){
            System.out.println("Invalid Input! Should start with Alphabet.In special characters,only _ is allowed.");
            string = sc.nextLine();
        }
        return string;
    }
}
