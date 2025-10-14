abstract class A {
    abstract void show();
}

class B extends A {
    // @Override
    public void show() {
        System.out.println("IN B");
    }
}

class C extends A {
    // @Override
    public void show() {
        System.out.println("IN C");
    }
}

class D extends B {
    // @Override
    // int show(int a){
    // return 1;
    // }
    static String name;
}

public class DiamondProblem {
    public static void main(String[] args) {
        B obj = new D();
        obj.show();
        // obj.show(1);
        D obj2 = new D();
        obj2 = null;
        System.out.println(obj2.name);
        java.util.Scanner sc = new java.util.Scanner(System.in);
    }
}
