package com.rtan.logic;

import com.rtan.entity.BinaryTreeNode;
import com.rtan.util.BinaryTreeUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BinaryTreeSortedLinkedList {

    public static void main(String[] args) {
//        List<Integer> preorder = Stream.of(10, 6, 4, 8, 14, 12, 16).collect(Collectors.toList());
//        List<Integer> inorder = Stream.of(4, 6, 8, 10, 12, 14, 16).collect(Collectors.toList());
        List<Integer> preorder = Stream.of(10, 6, 4, 14, 12).collect(Collectors.toList());
        List<Integer> inorder = Stream.of(4, 6, 10, 12, 14).collect(Collectors.toList());
        BinaryTreeNode tree = BinaryTreeUtils.treeWithPreorderAndInorder(preorder, inorder);
        BinaryTreeNode linkedList = binaryTree2SortedLinkedList(tree);
        System.out.println();
    }

    public static BinaryTreeNode binaryTree2SortedLinkedList(BinaryTreeNode tree) {
        // 返回链表的头部
        if (tree == null) {
            return tree;
        }
        // 左边的链表
        BinaryTreeNode left = binaryTree2SortedLinkedList(tree.getLeft());
        // 右边的链表
        BinaryTreeNode right = binaryTree2SortedLinkedList(tree.getRight());
        // 合成链表
        BinaryTreeNode head = tree;
        tree.setRight(right);
        tree.setLeft(left);
        if (right != null) {
            right.setLeft(tree);
        }
        if (left != null) {
            head = left;
            while (left.getRight() != null) {
                left = left.getRight();
            }
            left.setRight(tree);
        }
        return head;
    }

}
