package com.rtan.logic;

/**
 * 判断整数数组是否是某二叉树后序遍历的结果。数组数据不重复
 */
public class BinaryTreePostorder {

    public static void main(String[] args) {
        int[] is = {5, 7, 6, 9, 11, 10, 8};
        int[] no = {7, 4, 6, 5};
        System.out.println("is : " + isBinaryTreePostorder(is, 0, is.length - 1));
        System.out.println("no : " + isBinaryTreePostorder(no, 0, no.length - 1));
    }

    public static boolean isBinaryTreePostorder(int[] postorder, int from, int to) {
        if (postorder == null || from >= to) {
            return true;
        }
        int mid = postorder[to];
        // 获取左子树
        int i;
        for (i = from; i < to; i++) {
            if (postorder[i] > mid) {
                break;
            }
        }
        // 获取右子树
        for (int j = i; j < to; j++) {
            if (postorder[j] < mid) {
                return false;
            }
        }
        boolean left = isBinaryTreePostorder(postorder, from, i-1);
        boolean right = isBinaryTreePostorder(postorder, i, to - 1);
        return (left && right);
    }
}
