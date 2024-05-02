package com.blog;

public class TestClass {
    public static void main(String[] args) {
        TestClass testClass= new TestClass();
        int otp = testClass.test();
        System.out.println(otp);
    }
    public int test(){
        int otp=ExampleTest.example();
        return otp;
    }

}
