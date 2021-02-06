
import java.util.*;
import java.io.*;
public class RedBlackTreeN {
        class Node {
                int data; 
                Node parent;
                Node left; 
                Node right;
                int color; 
                int height = 1;
        }

        private Node root;
        private Node tn;

        private int calcheight(Node N) {
                if (N == null) {
                        return 0;
                }
                return N.height;
        }



        private Node searchTreeHelper(Node node, int key) {
                if (node == tn || key == node.data) {
                        return node;
                }

                if (key < node.data) {
                        return searchTreeHelper(node.left, key);
                }
                return searchTreeHelper(node.right, key);
        }

        private void fixDelete(Node x) {
                Node s;
                while (x != root && x.color == 0) {

                        if (x == x.parent.left) {
                                s = x.parent.right;
                                if (s.color == 1) {
                                        // case 3.1
                                        s.color = 0;
                                        x.parent.color = 1;
                                        leftRotate(x.parent);
                                        s = x.parent.right;
                                }

                                if (s.left.color == 0 && s.right.color == 0) {
                                        // case 3.2
                                        s.color = 1;
                                        x = x.parent;
                                } else {
                                        if (s.right.color == 0) {
                                                // case 3.3
                                                s.left.color = 0;
                                                s.color = 1;
                                                rightRotate(s);
                                                s = x.parent.right;
                                        }

                                        // case 3.4
                                        s.color = x.parent.color;
                                        x.parent.color = 0;
                                        s.right.color = 0;
                                        leftRotate(x.parent);
                                        x = root;
                                }
                        } else {
                                s = x.parent.left;
                                if (s.color == 1) {
                                        // case 3.1
                                        s.color = 0;
                                        x.parent.color = 1;
                                        rightRotate(x.parent);
                                        s = x.parent.left;
                                }

                                if (s.right.color == 0 && s.right.color == 0) {
                                        // case 3.2
                                        s.color = 1;
                                        x = x.parent;
                                } else {
                                        if (s.left.color == 0) {
                                                // case 3.3
                                                s.right.color = 0;
                                                s.color = 1;
                                                leftRotate(s);
                                                s = x.parent.left;
                                        }

                                        // case 3.4
                                        s.color = x.parent.color;
                                        x.parent.color = 0;
                                        s.left.color = 0;
                                        rightRotate(x.parent);
                                        x = root;
                                }
                        }
                }
                x.height = Math.max(calcheight(x.left), calcheight(x.right)) + 1;
                x.color = 0;
        }


        private void rbTransplant(Node u, Node v) {
                if (u.parent == null) {
                        root = v;
                } else if (u == u.parent.left) {
                        u.parent.left = v;
                } else {
                        u.parent.right = v;
                }
                v.parent = u.parent;
        }

        private void deleteNodeHelper(Node node, int key) {
                Node z = tn;
                Node x, y;
                while (node != tn) {
                        if (node.data == key) {
                                z = node;
                        }

                        if (node.data <= key) {
                                node = node.right;
                        } else {
                                node = node.left;
                        }
                }

                if (z == tn) {
                        System.out.println("Couldn't find key in the tree");
                        return;
                }

                y = z;
                int yOriginalColor = y.color;
                if (z.left == tn) {
                        x = z.right;
                        rbTransplant(z, z.right);
                } else if (z.right == tn) {
                        x = z.left;
                        rbTransplant(z, z.left);
                } else {
                        y = minimum(z.right);
                        yOriginalColor = y.color;
                        x = y.right;
                        if (y.parent == z) {
                                x.parent = y;
                        } else {
                                rbTransplant(y, y.right);
                                y.right = z.right;
                                y.right.parent = y;
                        }

                        rbTransplant(z, y);
                        y.left = z.left;
                        y.left.parent = y;
                        y.color = z.color;
                }
                node.height = Math.max(calcheight(node.left), calcheight(node.right)) + 1;
                if (yOriginalColor == 0) {
                        fixDelete(x);
                }
        }

        private void fixInsert(Node k) {
                Node u;
                while (k.parent.color == 1) {
                        if (k.parent == k.parent.parent.right) {
                                u = k.parent.parent.left; // uncle
                                if (u.color == 1) {
                                        // case 3.1
                                        u.color = 0;
                                        k.parent.color = 0;
                                        k.parent.parent.color = 1;
                                        k = k.parent.parent;
                                } else {
                                        if (k == k.parent.left) {
                                                // case 3.2.2
                                                k = k.parent;
                                                rightRotate(k);
                                        }
                                        // case 3.2.1
                                        k.parent.color = 0;
                                        k.parent.parent.color = 1;
                                        leftRotate(k.parent.parent);
                                }
                        } else {
                                u = k.parent.parent.right; // uncle

                                if (u.color == 1) {
                                        // mirror case 3.1
                                        u.color = 0;
                                        k.parent.color = 0;
                                        k.parent.parent.color = 1;
                                        k = k.parent.parent;
                                } else {
                                        if (k == k.parent.right) {
                                                // mirror case 3.2.2
                                                k = k.parent;
                                                leftRotate(k);
                                        }
                                        // mirror case 3.2.1
                                        k.parent.color = 0;
                                        k.parent.parent.color = 1;
                                        rightRotate(k.parent.parent);
                                }
                        }
                        if (k == root) {
                                break;
                        }
                }
                root.color = 0;
        }

        private void printHelper(Node root, String indent, boolean last) {
                if (root == null) {
                        System.out.println("No data available");
                }

                int height = root.height,
                        width = (int) Math.pow(2, height - 1);


                List < Node > current = new ArrayList < Node > (1), next = new ArrayList < Node > (2);
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
                        for (Node n: current) {

                                System.out.print(textBuffer);

                                if (n == null) {
                                        System.out.print("       ");
                                        next.add(null);
                                        next.add(null);
                                } else {
                                        String sColor = n.color == 1 ? "R" : "B";
                                        System.out.printf("(%6d" + sColor+"  )", n.data);
                                        next.add(n.left);
                                        next.add(n.right);
                                }

                                System.out.print(textBuffer);
                        }

                        System.out.println();
                        if (i < height - 1) {

                                for (Node n: current) {

                                        System.out.print(textBuffer);

                                        if (n == null) {
                                                System.out.print("        ");
                                        } else {
                                                System.out.printf("%s      %s", n.left == null ? " " : "/", n.right == null ? " " : "\\");
                                        }

                                        System.out.print(textBuffer);

                                }

                                System.out.println();

                        }
                        
                        elements *= 2;
                        current = next;
                        next = new ArrayList < Node > (elements);       
                }
        }

        public RedBlackTreeN() {
                tn = new Node();
                tn.color = 0;
                tn.left = null;
                tn.right = null;
                root = tn;
        }



        public Node searchTree(int k) {
                return searchTreeHelper(this.root, k);
        }

        public Node minimum(Node node) {
                while (node.left != tn) {
                        node = node.left;
                }
                return node;
        }
        public Node maximum(Node node) {
                while (node.right != tn) {
                        node = node.right;
                }
                return node;
        }
        public Node successor(Node x) {

                if (x.right != tn) {
                        return minimum(x.right);
                }
                Node y = x.parent;
                while (y != tn && x == y.right) {
                        x = y;
                        y = y.parent;
                }
                return y;
        }

        public Node predecessor(Node x) {

                if (x.left != tn) {
                        return maximum(x.left);
                }

                Node y = x.parent;
                while (y != tn && x == y.left) {
                        x = y;
                        y = y.parent;
                }

                return y;
        }


        public void leftRotate(Node x) {
                Node y = x.right;
                x.right = y.left;
                if (y.left != tn) {
                        y.left.parent = x;
                }
                y.parent = x.parent;
                if (x.parent == null) {
                        this.root = y;
                } else if (x == x.parent.left) {
                        x.parent.left = y;
                } else {
                        x.parent.right = y;
                }
                y.left = x;
                x.parent = y;
                y.height = Math.max(calcheight(y.left), calcheight(y.right)) + 1;
                x.height = Math.max(calcheight(x.left), calcheight(x.right)) + 1;
        }


        public void rightRotate(Node x) {
                Node y = x.left;
                x.left = y.right;
                if (y.right != tn) {
                        y.right.parent = x;
                }
                y.parent = x.parent;
                if (x.parent == null) {
                        this.root = y;
                } else if (x == x.parent.right) {
                        x.parent.right = y;
                } else {
                        x.parent.left = y;
                }
                y.right = x;
                x.parent = y;
                y.height = Math.max(calcheight(y.left), calcheight(y.right)) + 1;
                x.height = Math.max(calcheight(x.left), calcheight(x.right)) + 1;
        }


        public void insert(int key) {

                Node node = new Node();
                node.parent = null;
                node.data = key;
                node.left = tn;
                node.right = tn;
                node.color = 1;

                Node y = null;
                Node x = this.root;

                while (x != tn) {
                        y = x;
                        if (node.data < x.data) {
                                x = x.left;
                        } else {
                                x = x.right;
                        }
                }

                node.parent = y;
                if (y == null) {
                        root = node;
                } else if (node.data < y.data) {
                        y.left = node;
                } else {
                        y.right = node;
                }
                if (node.parent == null) {
                        node.color = 0;
                        return;
                }

                if (node.parent.parent == null) {
                        return;
                }

                node.height = Math.max(calcheight(node.left), calcheight(node.right)) + 1;
                fixInsert(node);
        }

        public Node getRoot() {
                return this.root;
        }

        public void deleteNode(int data) {
                deleteNodeHelper(this.root, data);
        }

        public void prettyPrint() {
                printHelper(this.root, "", true);
        }

        public static void main(String[] args) {

                RedBlackTreeN t = new RedBlackTreeN();
                while (true) {
                        System.out.println("1.Insert \t\t 2. Delete \t\t 3. Display");
                        System.out.printf("\t\t\t\t\t\t\t   Enter your choice : ");
                        Scanner sc = new Scanner(System.in);
                        int choice = sc.nextInt();

                        if (choice == 1) {
                                System.out.print("Enter your data : ");
                                Scanner sca = new Scanner(System.in);
                                String input = sca.nextLine();
                                String[] strarr = input.split(" ");
                                int inp[] = new int[strarr.length];
                                for (int i = 0; i < strarr.length; i++) {
                                        t.insert(Integer.parseInt(strarr[i]));
                                }
                                // t.insert(0);
                                // t.deleteNode(0);
                                t.prettyPrint();
                        }
                        if (choice == 2) {
                                System.out.print("Enter data to delete: ");
                                Scanner in = new Scanner(System.in);
                                int datadelete = in.nextInt();
                                t.deleteNode(datadelete);
                                t.prettyPrint();
                        }
                        if (choice == 3) {
                                t.prettyPrint();
                        }
                        if (choice == -1) {
                                break;
                        }
                }
        }
}