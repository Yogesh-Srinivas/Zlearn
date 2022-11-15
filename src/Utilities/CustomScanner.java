package Utilities;

import java.util.Scanner;

public class CustomScanner {
    public static int getIntegetInput(int minValue, int maxValue){
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
                System.out.println("Invalid Input!!");
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

    public static String getOptions(String inputOptions) {
        Scanner sc = new Scanner(System.in);
        String input="";
        boolean isValidInput = false;
        while(!isValidInput){
            input = sc.next();
            if(inputOptions.contains(input)) isValidInput=true;
            else System.out.println("Invalid option!!");
        }
        return input;
    }
}
