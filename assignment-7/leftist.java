import java.util.Scanner;
import java.util.*;
import java.io.*;

class LeftHeapNode {
        int element, sValue;
        LeftHeapNode left, right;
        int height = 1;

        public LeftHeapNode(int ele) {
                this(ele, null, null);
        }

        public LeftHeapNode(int ele, LeftHeapNode left, LeftHeapNode right) {
                this.element = ele;
                this.left = left;
                this.right = right;
                this.sValue = 0;
        }
}

class LeftistHeap {
        private LeftHeapNode root;

        public LeftistHeap() {
                root = null;
        }

        public boolean isEmpty() {
                return root == null;
        }

        public void clear() {
                root = null;
        }

        public void insert(int x) {
                root = merge(new LeftHeapNode(x), root);
        }

        public void merge(LeftistHeap rhs) {
                if (this == rhs)
                        return;
                root = merge(root, rhs.root);
                root.height = Math.max(calcheight(root.left), calcheight(root.right)) + 1;
                rhs.root = null;
        }

        private LeftHeapNode merge(LeftHeapNode x, LeftHeapNode y) {
                if (x == null)
                        return y;
                if (y == null)
                        return x;
                if (x.element > y.element) {
                        LeftHeapNode temp = x;
                        x = y;
                        y = temp;
                }

                x.right = merge(x.right, y);

                if (x.left == null) {
                        x.left = x.right;
                        x.right = null;
                } else {
                        if (x.left.sValue < x.right.sValue) {
                                LeftHeapNode temp = x.left;
                                x.left = x.right;
                                x.right = temp;
                        }
                        x.sValue = x.right.sValue + 1;
                }
                x.height = Math.max(calcheight(x.left), calcheight(x.right)) + 1;
                return x;
        }

        public int deleteMin() {
                if (isEmpty())
                        return -1;
                int minItem = root.element;
                root = merge(root.left, root.right);
                return minItem;
        }

        private int calcheight(LeftHeapNode N) {
                if (N == null) {
                        return 0;
                }
                return N.height;
        }

        public void inorder() {
                print(root);
                System.out.println();
        }

        private void print(LeftHeapNode root) {

                if (root == null) {
                        System.out.println("(XXXXXX)");
                        return;
                }

                int height = root.height, width = (int) Math.pow(2, height - 1);
                List < LeftHeapNode > current = new ArrayList < LeftHeapNode > (1), next = new ArrayList < LeftHeapNode > (2);
                current.add(root);
                final int maxHalfLength = 4;
                int elements = 1;

                StringBuilder sb = new StringBuilder(maxHalfLength * width);
                for (int i = 0; i < maxHalfLength * width; i++) {
                        sb.append(' ');
                }

                String textBuffer;

                // Iterating through height levels.
                for (int i = 0; i < height; i++) {

                        sb.setLength(maxHalfLength * ((int) Math.pow(2, height - 1 - i) - 1));

                        // Creating spacer space indicator.
                        textBuffer = sb.toString();

                        // Print tree node elements
                        for (LeftHeapNode n: current) {

                                System.out.print(textBuffer);

                                if (n == null) {
                                        System.out.print("        ");
                                        next.add(null);
                                        next.add(null);
                                } else {

                                        System.out.printf("(%6d)", n.element);
                                        next.add(n.left);
                                        next.add(n.right);

                                }

                                System.out.print(textBuffer);

                        }

                        System.out.println();

                        // Print tree node extensions for next level.
                        if (i < height - 1) {

                                for (LeftHeapNode n: current) {
                                        System.out.print(textBuffer);

                                        if (n == null) {
                                                System.out.print("        ");
                                        } else {
                                                System.out.printf("%s      %s",
                                                        n.left == null ? " " : "/", n.right == null ? " " : "\\");
                                        }

                                        System.out.print(textBuffer);

                                }

                                System.out.println();

                        }

                        elements *= 2;
                        current = next;
                        next = new ArrayList < LeftHeapNode > (elements);
                }
        }




}

public class leftist {

        public static void main(String[] args) {
                Scanner scan = new Scanner(System.in);
                System.out.println("LeftistHeap Test\n\n");
                LeftistHeap lh = new LeftistHeap();

                char ch;
                while (true) {
                        System.out.println("\nLeftist Heap Operations\n");
                        System.out.println("1. insert ");
                        System.out.println("2. delete min");
                        int choice = scan.nextInt();
                        switch (choice) {
                                case 1:
                                        System.out.println("Enter integer element to insert");
                                        lh.insert(scan.nextInt());
                                        lh.inorder();
                                        break;
                                case 2:
                                        lh.deleteMin();
                                        lh.inorder();
                                        break;
                                case 3:
                                        System.out.println("Empty status = " + lh.isEmpty());
                                        break;
                                case -1:
                                        break;
                                default:
                                        System.out.println("Wrong Entry \n ");
                                        break;
                        }
                }
        }
}