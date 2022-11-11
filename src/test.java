import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        getMultiLineInput();
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
}
