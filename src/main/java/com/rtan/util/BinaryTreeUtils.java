package com.rtan.util;

import com.rtan.entity.BinaryTreeNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 二叉树工具类
 */
public class BinaryTreeUtils {

    public static void main(String[] args) {
        List<Integer> preorder = Stream.of(8, 6, 5, 7, 10, 9, 11).collect(Collectors.toList());
        List<Integer> inorder = Stream.of(5, 6, 7, 8, 9, 10, 11).collect(Collectors.toList());
        BinaryTreeNode tree = treeWithPreorderAndInorder(preorder, inorder);
        System.out.println(binaryTree2LevelStringQueue(tree));
    }

    /**
     * 根据二叉树的先序遍历和中序遍历构建二叉树
     *
     * @param preorder 二叉树先序遍历
     * @param inorder  二叉树中序遍历
     * @return Binary Tree or null
     */
    public static BinaryTreeNode treeWithPreorderAndInorder(List<Integer> preorder, List<Integer> inorder) {
        // 检验数据合法性
        if (preorder == null || inorder == null || preorder.size() != inorder.size()) {
            throw new IllegalArgumentException(String.format("Null or Unequal: preorder= %s, inorder= %s", preorder, inorder));
        }
        // 递归的返回条件
        if (preorder.isEmpty()) {
            return null;
        }
        List<Integer> preMoreNode = preorder.stream().filter(node -> !inorder.contains(node)).collect(Collectors.toList());
        List<Integer> inMoreNode = inorder.stream().filter(node -> !preorder.contains(node)).collect(Collectors.toList());
        if (!preMoreNode.isEmpty() || !inMoreNode.isEmpty()) {
            throw new IllegalArgumentException(String.format("Data inconsistency: preorder= %s, inorder= %s", preorder, inorder));
        }

        Integer t = preorder.get(0);
        BinaryTreeNode node = new BinaryTreeNode();
        node.setValue(t);
        int len = inorder.size();
        for (int i = 0; i < len; i++) {
            if (t.equals(inorder.get(i))) {
                // 左子树
                BinaryTreeNode left = treeWithPreorderAndInorder(subList(preorder, 1, i + 1), subList(inorder, 0, i));
                node.setLeft(left);
                // 右子树
                BinaryTreeNode right = treeWithPreorderAndInorder(subList(preorder, i + 1, len), subList(inorder, i + 1, len));
                node.setRight(right);
                // 不考虑数据有重复
                // TODO 数据重复
                break;
            }
        }
        return node;
    }

    /**
     * 二叉树层次遍历
     *
     * @param tree 二叉树
     * @return 遍历字符串
     */
    public static String binaryTree2LevelString(BinaryTreeNode tree) {
        if (tree == null) {
            return "";
        }
        final String[] str = {""};
        List<BinaryTreeNode> nodes = new ArrayList<>(Collections.singletonList(tree));
        while (!nodes.isEmpty()) {
            List<BinaryTreeNode> tempNodes = new ArrayList<>();
            nodes.forEach(node -> {
                str[0] += String.format("[%s]  ", String.valueOf(node.getValue()));
                if (node.getLeft() != null) {
                    tempNodes.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    tempNodes.add(node.getRight());
                }
            });
            str[0] += "\n";
            nodes = tempNodes;
        }
        return str[0];
    }

    /**
     * 通过队列层次遍历
     *
     * @param tree 二叉树
     * @return 层次遍历的字符串
     */
    public static String binaryTree2LevelStringQueue(BinaryTreeNode tree) {
        if (tree == null) {
            return "";
        }
        Queue<BinaryTreeNode> queue = new ArrayDeque();
        queue.offer(tree);
        String str = "";
        while (queue.peek() != null) {
            BinaryTreeNode node = queue.poll();
            str += "  " + node.getValue();
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
        return str;
    }

    /**
     * 根据fromIndex和toIndex截取列表，包括fromIndex，不包括toIndex，其它返回空集合
     *
     * @param list      截取的列表
     * @param fromIndex 开始索引,大于零
     * @param toIndex   结尾索引,小于集合长度
     * @param <T>       数据类型
     * @return subList or emptyList
     */
    public static <T> List<T> subList(List<T> list, int fromIndex, int toIndex) {
        List<T> sub = new ArrayList<>();
        if (fromIndex >= 0 && fromIndex < toIndex && toIndex <= list.size()) {
            for (int i = fromIndex; i < toIndex; i++) {
                sub.add(list.get(i));
            }
        }
        return sub;
    }
}
