package com.rtan.logic;

import java.util.Objects;
import java.util.Stack;

/**
 * 获取栈中的最小元素。min，pop，push时间复杂度都是o(1)
 */
public class MinOfStack {

    public static void main(String[] args) {
        Integer[] data = new Integer[]{2, 1, 3, 4, 0, 1, 5};
        CustomStack<Integer> stack = new CustomStack<>();
        for (Integer datum : data) {
            stack.push(datum);
            System.out.println("[push] min : " + stack.min());
        }
        for (int i = 0; i < data.length; i++) {
            Integer pop = stack.pop();
            if (pop != null) {
                System.out.println("[pop] min : " + stack.min());
            }
        }
    }

    public static class CustomStack<T extends Comparable> {
        private Stack<T> dataStack = new Stack<>();
        private Stack<T> minStack = new Stack<>();

        public T min() {
            if(minStack.empty()){
                return null;
            }
            return minStack.peek();
        }

        public T pop() {
            if (dataStack.empty()) {
                return null;
            }
            minStack.pop();
            return dataStack.pop();
        }

        public void push(T t) {
            Objects.requireNonNull(t);
            dataStack.push(t);
            if (minStack.isEmpty() || t.compareTo(minStack.peek()) <= 0) {
                minStack.push(t);
            }else {
                minStack.push(minStack.peek());
            }
        }

    }
}
