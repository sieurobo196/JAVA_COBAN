/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codewr.java_coban;

/**
 *
 * @author codewr
 */
public class Fibonacci {

    public static void main(String[] args) {
        System.out.println("10 số đầu tiên của dãy số fibonacci: ");
        for (int i = 0; i < 10; i++) {
            System.out.println(fibonacci_func3(i));
        }

    }

    // only n>=0
    public static int fibonacci_func1(int n) {
        int f0 = 0;//n=0
        int f1 = 1; //n=1
        int fn = 1; //n=2
        if (n == 0) {
            fn = 0;
        } else if (n == 1) {
            fn = 1;
        } else {
            for (int i = 2; i < n; i++) {
                f0 = f1;
                f1 = fn;
                fn = f0 + f1;
            }
        }
        return fn;

    }

    // n la so nguyen
    public static int fibonacci_func2(int n) {
        int f0 = 0;
        int f1 = 1;
        int fn = 1;

        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return n;
        } else {
            for (int i = 2; i < n; i++) {
                f0 = f1;
                f1 = fn;
                fn = f0 + f1;
            }
        }
        return fn;
    }

    //pp đệ quy
    public static int fibonacci_func3(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return n;
        } else {
            return fibonacci_func3(n - 1) + fibonacci_func3(n - 2);
        }
    }

    static int n1 = 0, n2 = 1, n3 = 0;

    static void fibonacci_func4(int count) {
        if (count > 0) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
            System.out.print(" " + n3);
            fibonacci_func4(count - 1);
        }
    }

}
