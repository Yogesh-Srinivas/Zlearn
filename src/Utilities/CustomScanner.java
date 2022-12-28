package Utilities;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomScanner {
    public static int getIntegerInput(int minValue, int maxValue){
        int integerInput=-1;
        boolean isValidIntegerInput = false;
        while (!isValidIntegerInput){
            try{
                integerInput = new Scanner(System.in).nextInt();
                if(integerInput>maxValue || integerInput<minValue){
                    throw new Exception();
                }
                isValidIntegerInput = true;
            }catch (Exception e){
                System.out.println("Invalid Input!! Value must be between "+minValue+" and "+maxValue+".");
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
}
