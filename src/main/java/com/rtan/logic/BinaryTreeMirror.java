package com.rtan.logic;

import com.rtan.entity.BinaryTreeNode;
import com.rtan.util.BinaryTreeUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给定一个二叉树，输出它的镜像
 */
public class BinaryTreeMirror {

    public static void main(String[] args) {
        List<Integer> preorder = Stream.of(1, 2, 4, 7, 5, 8, 3, 6, 9, 10).collect(Collectors.toList());
        List<Integer> inorder = Stream.of(4, 7, 2, 8, 5, 1, 3, 9, 6, 10).collect(Collectors.toList());
        BinaryTreeNode tree = BinaryTreeUtils.treeWithPreorderAndInorder(preorder, inorder);
        binaryTree2Mirror(tree);
        System.out.println(BinaryTreeUtils.binaryTree2LevelString(tree));
    }

    public static void binaryTree2Mirror(BinaryTreeNode tree){
        if(tree == null){
            return;
        }
        binaryTree2Mirror(tree.getLeft());
        binaryTree2Mirror(tree.getRight());
        BinaryTreeNode left = tree.getLeft();
        tree.setLeft(tree.getRight());
        tree.setRight(left);
    }

}
