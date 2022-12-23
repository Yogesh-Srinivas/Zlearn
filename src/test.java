
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        System.out.println("assads".contains(" "));
//        getMultiLineInput();
//        CourseDatabase cdb = CourseDatabase.getInstance();
//        cdb.getCourse("ZCourse_001");
            A a = new B();
            a.use();
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

