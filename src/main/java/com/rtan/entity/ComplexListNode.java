package com.rtan.entity;

public class ComplexListNode {

    private int value;

    private ComplexListNode next;

    private ComplexListNode sibling;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ComplexListNode getNext() {
        return next;
    }

    public void setNext(ComplexListNode next) {
        this.next = next;
    }

    public ComplexListNode getSibling() {
        return sibling;
    }

    public void setSibling(ComplexListNode sibling) {
        this.sibling = sibling;
    }
}
