package com.rtan.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串的排列方式
 */
public class StringList {

    public static void main(String[] args) {
        List<Character> list = new ArrayList<>();
        list.add('a');
        list.add('b');
        list.add('c');
//        list.add('d');
//        allStringList(list, new ArrayList<>());
        char[] chars = {'a', 'b', 'c'};
        stringListBySwap(chars, 0);
    }

    public static void allStringList(List<Character> characters, List<Character> str) {
        if (characters == null || characters.isEmpty()) {
            System.out.println(str);
            str.remove(str.size() - 1);
            return;
        }
        for (int i = 0; i < characters.size(); i++) {
            Character ch = characters.get(i);
            str.add(ch);
            characters.remove(i);
            allStringList(characters, str);
            characters.add(i, ch);
        }
        if (!str.isEmpty()) {
            str.remove(str.size() - 1);
        }
    }

    // 交换方式不占用额外空间
    public static void stringListBySwap(char[] chars, int n) {
        if (chars.length == 0 || n == chars.length) {
            System.out.println(chars);
            return;
        }
        for (int i = n; i < chars.length; i++) {
            char ch = chars[i];
            chars[i] = chars[n];
            chars[n] = ch;
            stringListBySwap(chars, n + 1);
            char chp = chars[i];
            chars[i] = chars[n];
            chars[n] = chp;
        }
    }

}
