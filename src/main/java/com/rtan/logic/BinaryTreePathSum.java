package com.rtan.logic;

import com.rtan.entity.BinaryTreeNode;
import com.rtan.util.BinaryTreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 二叉树根节点到叶子节点路径中节点的和等于所给整数
 */
public class BinaryTreePathSum {
    public static void main(String[] args) {
        List<Integer> preorder = Stream.of(10, 5, 4, 7, 12).collect(Collectors.toList());
        List<Integer> inorder = Stream.of(4, 5, 7, 10, 12).collect(Collectors.toList());
        BinaryTreeNode tree = BinaryTreeUtils.treeWithPreorderAndInorder(preorder, inorder);
        printTreePathWithSum(tree, 22);
    }

    public static void printTreePathWithSum(BinaryTreeNode tree, int sum) {
        if (tree == null) {
            System.out.println("The tree is null!");
        }
        List<BinaryTreeNode> path = new ArrayList<>();
        treePathWithSum(tree, sum, path);
    }

    public static void treePathWithSum(BinaryTreeNode node, int sum, List<BinaryTreeNode> path) {
        if (node == null) {
            if (sum == 0) {
                path.forEach(x -> {
                    System.out.print("  " + x.getValue());
                });
                System.out.println();
            }
            return;
        }
        sum -= node.getValue();
        path.add(node);
        treePathWithSum(node.getLeft(), sum, path);
        treePathWithSum(node.getRight(), sum, path);
        path.remove(path.size() - 1);
    }

}
