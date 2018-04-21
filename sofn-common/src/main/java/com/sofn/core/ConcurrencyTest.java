package com.sofn.core;

/**
 * @author sofn
 * @version 2017年02月22日 下午 4:39
 */
public class ConcurrencyTest {
    ConcurrencyTest(){
        System.out.print("A  ");
    }
    ConcurrencyTest(int i){
        this();
        System.out.print("AA  ");
    }
    public static void main(String [] args) throws InterruptedException{
        ConcurrencyTest test = new ConcurrencyTest();
        Object a = null;
        ConcurrencyTest c = new C();
//        c.func1(a);
    }

    public void func1(String a){
        System.out.println("a");
    };
}

class B extends ConcurrencyTest{
    B(){
        super();
        System.out.print("B  ");
    }
    B(int i){
        super(i);
        System.out.print("BB  ");
    }
}
class C extends ConcurrencyTest{
    public void func1(Object a){
        System.out.println("c");
    }
}