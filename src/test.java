
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
class test {
    public static void main(String[] args) {
     B b = new B(10,20);
     A a = b;
     A a2 = new A(11);
     B b2 = (B) a2;
        System.out.println(b2.x+" "+b2.y);
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
    int y;
    B(int x,int y) {
        super(x);
        this.y =y;
    }

    @Override
    void use(){
        System.out.println("use b");
    }
}

