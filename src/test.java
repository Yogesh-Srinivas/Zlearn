
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
class test {
    public static void main(String[] args) {
        ArrayList<A> arr = new ArrayList<>();
        arr.add(new A(10));
        arr.add(new A(20));
        ArrayList<A> newArr = new ArrayList<>(arr);
        for(A a:newArr){
            a.x=(a.x)+10;
        }
        for (A a: arr)
        System.out.println(a.x);
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
    int x;
    A(int x){
        this.x=x;
    }
    void use(){
        System.out.println("use a");
    }
}

class B extends A{
    B(int x) {
        super(x);
    }

    @Override
    void use(){
        System.out.println("use b");
    }
}

