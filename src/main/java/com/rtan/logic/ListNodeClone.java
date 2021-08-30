package com.rtan.logic;

import com.rtan.entity.ComplexListNode;

import java.util.*;

public class ListNodeClone {

    public static void main(String[] args) {
        // 需要复制的链表
        ComplexListNode a = new ComplexListNode();
        ComplexListNode b = new ComplexListNode();
        ComplexListNode c = new ComplexListNode();
        ComplexListNode d = new ComplexListNode();
        ComplexListNode e = new ComplexListNode();
        a.setValue(1);
        a.setNext(b);
        a.setSibling(c);
        b.setValue(2);
        b.setNext(c);
        b.setSibling(e);
        c.setValue(3);
        c.setNext(d);
        c.setSibling(null);
        d.setValue(4);
        d.setNext(e);
        d.setSibling(b);
        e.setValue(5);
        e.setNext(null);
        e.setSibling(null);
        ComplexListNode clone = complexListNodeClone(a);
        System.out.println("Complete clone: "+clone);
    }

    /**
     *
     * 指向关系存放在map中，需要O(n)的辅助空间，（如何不要辅助空间？？）
     * @param pHead
     * @return
     */
    public static ComplexListNode complexListNodeClone(ComplexListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ComplexListNode tempHead, tempCloneHead;
        Map<ComplexListNode, List<ComplexListNode>> orig2PointToMap = new HashMap<>();
        //复制head
        ComplexListNode cloneHead = new ComplexListNode();
        cloneHead.setValue(pHead.getValue());
        cloneHead.setNext(null);
        cloneHead.setSibling(null);
        orig2PointToMap.put(pHead, new ArrayList<>());
        if (pHead.getSibling() != null) {
            orig2PointToMap.put(pHead.getSibling(), new ArrayList<>(Collections.singletonList(cloneHead)));
        }
        // 复制整个链表，将指向关系存储在map中
        tempHead = pHead;
        tempCloneHead = cloneHead;
        while (tempHead.getNext() != null) {
            ComplexListNode next = tempHead.getNext();
            // 复制next
            ComplexListNode cloneNext = new ComplexListNode();
            cloneNext.setValue(next.getValue());
            cloneNext.setNext(null);
            cloneNext.setSibling(null);
            tempCloneHead.setNext(cloneNext);
            tempCloneHead = cloneNext;
            // 将该next放进map
            orig2PointToMap.putIfAbsent(next, new ArrayList<>());
            // next的指向
            if(next.getSibling()!=null) {
                List<ComplexListNode> nextKey = orig2PointToMap.get(next.getSibling());
                if (nextKey == null) {
                    orig2PointToMap.put(next.getSibling(), new ArrayList<>(Collections.singletonList(cloneNext)));
                } else {
                    nextKey.add(cloneNext);
                }
            }
            // 循环条件
            tempHead = next;
        }
        // 解析map
        tempHead = pHead;
        tempCloneHead = cloneHead;
        while (tempHead != null) {
            List<ComplexListNode> nodes = orig2PointToMap.get(tempHead);
            for (ComplexListNode node : nodes) {
                node.setSibling(tempCloneHead);
            }
            tempHead = tempHead.getNext();
            tempCloneHead = tempCloneHead.getNext();
        }
        return cloneHead;
    }

}
