package com.liyao;

public class BinaryTreeTest {

    static  class Node {
        boolean visited = false;
        int data = 0;
        Node left = null;
        Node right = null;
    }


    // 求二叉树的深度
    public static int deep(Node node) {
        int h1, h2;
        if (node == null) {
            return 0;
        } else {
            h1 = deep(node.left);
            h2 = deep(node.right);
            return (h1 < h2) ? h2 + 1 : h1 + 1;
        }
    }


    public static void main(String[] args) {
        Node root = new Node();
        root.data = 9;

        Node temp01 = new Node();
        temp01.data = 1;
        root.left = temp01;

        Node temp02 = new Node();
        temp02.data = 3;
        root.right = temp02;

        Node temp03 = new Node();
        temp03.data = 2;
        root.left.left = temp03;

        Node temp04 = new Node();
        temp04.data = 4;
        root.left.right = temp04;

        Node temp05 = new Node();
        temp05.data = 8;
        root.right.left = temp05;

        Node temp06 = new Node();
        temp06.data = 6;
        root.left.left.left = temp06;

        Node temp07 = new Node();
        temp07.data = 7;
        root.left.left.right = temp07;

        Node temp08 = new Node();
        temp07.data = 7;
        root.left.left.right.right = temp08;

        System.out.println(deep(root));

    }


}
