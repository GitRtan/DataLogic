package com.rtan.logic;

import java.util.Stack;

/**
 * 根据栈的入栈顺序判断某个出栈顺序，栈元素不重复.(双指针，模拟入栈出栈场景)
 */
public class OrderPopStack {

    public static void main(String[] args) {
        int[] in = {1, 2, 3, 4, 5};
        int[] outTrue = {4, 5, 3, 2, 1};
        int[] outFalse = {4, 3, 5, 1, 2};
        System.out.println("pop order true : " + isPopOrder(in, outTrue));
        System.out.println("pop order false: " + isPopOrder(in, outFalse));
    }

    public static boolean isPopOrder(int[] in, int[] out) {
        if (in == null || out == null || in.length != out.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int inp = 0, outp = 0;
        while (inp < in.length) {
            stack.push(in[inp]);
            while (!stack.isEmpty() && stack.peek() == out[outp]) {
                stack.pop();
                outp++;
            }
            inp++;
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }

}
