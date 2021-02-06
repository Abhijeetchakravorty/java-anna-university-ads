import java.util.Scanner;
class StudentDatabase {
        public static void main(String[] args) {

                Scanner input = new Scanner(System.in);

                AvlTreeNode root = null;

                int rollNumber;
                String name;
                float cgpa;
                int semester;

                while (true) {
                        System.out.printf("\n\nOptions:\n1.Insert New Student Record\n2.Search Student Record\n3. Delete a record\n4.Exit\nPlease Select Correct Option: ");
                        int choice = input.nextInt();

                        switch (choice) {
                                case 1:
                                        System.out.printf("Enter Student Roll Number: ");
                                        rollNumber = input.nextInt();
                                        System.out.printf("Enter Student Name: ");
                                        name = input.next();
                                        System.out.printf("Enter the Student Current Semester: ");
                                        semester = input.nextInt();
                                        System.out.printf("Enter the Student CGPA: ");
                                        cgpa = input.nextFloat();
                                        root = AvlTreeOperation.insertNode(root, rollNumber, name, cgpa, semester);
                                        System.out.println("Student Records After Inserting New record: ");
                                        AvlTreeOperation.display(root);
                                        break;
                                case 2:
                                        if (root == null)
                                                System.out.println("Tree is Empty.");
                                        else {
                                                System.out.printf("Enter Student Roll Number to Display Information: ");
                                                rollNumber = input.nextInt();
                                                AvlTreeOperation.find(root, rollNumber);
                                        }
                                        break;

                                case 3:
                                        System.out.printf("Enter Roll Number of Student to delete: ");
                                        rollNumber = input.nextInt();
                                        root = AvlTreeOperation.deleteNode(root, rollNumber);
                                        System.out.println("Student Record After deleting Roll Number " + rollNumber + " record:  ");
                                        AvlTreeOperation.display(root);
                                        break;

                                case 4:
                                        System.exit(0);
                                default:
                                        System.out.printf("Wrong Input. Make sure you select the option from above.\n ");
                                        break;
                        }
                }

        }
}



class AvlTreeNode {

        int rollNumber;
        String name;
        float cgpa;
        int semester;
        int height;
        AvlTreeNode left;
        AvlTreeNode right;

        AvlTreeNode(int rollNumber, String name, float cgpa, int semester) {
                this.rollNumber = rollNumber;
                this.name = name;
                this.cgpa = cgpa;
                this.semester = semester;
                height = 0;
                left = null;
                right = null;
        }
}

class AvlTreeOperation {

        static AvlTreeNode insertNode(AvlTreeNode root, int rollNumber, String name, float cgpa, int semester) {

                AvlTreeNode newNode = new AvlTreeNode(rollNumber, name, cgpa, semester);

                if (root == null) {
                        root = newNode;
                } else if (rollNumber < root.rollNumber) {
                        root.left = insertNode(root.left, rollNumber, name, cgpa, semester);
                        if (balanceFactor(root) > 1) {
                                if (rollNumber < root.left.rollNumber)
                                        root = AvlTreeRotation.leftRotation(root);
                                else {
                                        root.left = AvlTreeRotation.rightRotation(root.left);
                                        root = AvlTreeRotation.leftRotation(root);
                                }
                        }
                } else if (rollNumber > root.rollNumber) {
                        root.right = insertNode(root.right, rollNumber, name, cgpa, semester);

                        if (balanceFactor(root) < -1) {
                                if (rollNumber > root.right.rollNumber)
                                        root = AvlTreeRotation.rightRotation(root);
                                else {
                                        root.right = AvlTreeRotation.leftRotation(root.right);
                                        root = AvlTreeRotation.rightRotation(root);
                                }
                        }
                }
                root.height = height(root);

                return root;
        }

        static AvlTreeNode deleteNode(AvlTreeNode root, int rollNumber) {
                if (root == null) {
                        System.out.printf("Data does not Exist in tree\n");
                        return null;
                }

                if (rollNumber < root.rollNumber) {
                        root.left = deleteNode(root.left, rollNumber);

                        if (balanceFactor(root) < -1 && root != null) {
                                if (height(root.right.left) <= height(root.right.right))
                                        root = AvlTreeRotation.rightRotation(root);
                                else {
                                        root.right = AvlTreeRotation.leftRotation(root.right);
                                        root = AvlTreeRotation.rightRotation(root);
                                }
                        }

                } else if (rollNumber > root.rollNumber) {
                        root.right = deleteNode(root.right, rollNumber);

                        if (balanceFactor(root) > 1) {
                                if (height(root.left.left) >= height(root.left.right))
                                        root = AvlTreeRotation.leftRotation(root);
                                else {
                                        root.left = AvlTreeRotation.rightRotation(root.left);
                                        root = AvlTreeRotation.leftRotation(root);
                                }
                        }
                } else {

                        if (root.left == null)
                                return root.right;
                        else if (root.right == null)
                                return root.left;
                        else {
                                AvlTreeNode predecessor = root.left;
                                while (predecessor.right != null)
                                        predecessor = predecessor.right;
                                root.rollNumber = predecessor.rollNumber;
                                root.left = deleteNode(root.left, predecessor.rollNumber);
                        }
                }

                root.height = height(root);
                return root;
        }

        static int height(AvlTreeNode root) {

                int leftHeight = 0, rightHeight = 0;
                if (root != null) {
                        leftHeight = root.left == null ? 0 : root.left.height;
                        rightHeight = root.right == null ? 0 : root.right.height;

                        return (leftHeight > rightHeight) ? (1 + leftHeight) : (1 + rightHeight);

                }
                return 0;
        }

        static int balanceFactor(AvlTreeNode root) {

                return (height(root.left) - height(root.right));
        }

        static void find(AvlTreeNode root, int rollNumber) {
                if (root == null) {
                        System.out.println("The Data You are looking for does not Exist inside the Record");
                        return;
                }

                if (root.rollNumber == rollNumber) {
                        System.out.println("\nStudent Record with Roll Number: " + rollNumber);
                        System.out.println("\nStudent's Roll Number: " + root.rollNumber);
                        System.out.println("Student's Name : " + root.name);
                        System.out.println("Student's Current Semester: " + root.rollNumber);
                        System.out.println("Student's CGPA: " + root.rollNumber);
                } else if (root.rollNumber > rollNumber)
                        AvlTreeOperation.find(root.left, rollNumber);
                else if (root.rollNumber < rollNumber) {
                        AvlTreeOperation.find(root.right, rollNumber);

                }
        }


        static void display(AvlTreeNode root) {
                if (root == null) {
                        System.out.println("Record is Empty:");
                        return;
                }
                final int height, width;
                height = root.height;
                width = (int) Math.pow(2, height + 2);
                int len = width * height * 2 + 2;
                StringBuilder sb = new StringBuilder(len);
                for (int i = 1; i <= len; i++)
                        sb.append(i < len - 2 && i % width == 0 ? "\n" : ' ');

                displayR(sb, width / 2, 1, width / 4, width, root, " ");
                System.out.println(sb);
        }

        static void displayR(StringBuilder sb, int c, int r, int d, int w, AvlTreeNode n,
                String edge) {
                if (n != null) {
                        displayR(sb, c - d, r + 2, d / 2, w, n.left, " /");

                        String s = String.valueOf(n.rollNumber);
                        int idx1 = r * w + c - (s.length() + 1) / 2;
                        int idx2 = idx1 + s.length();
                        int idx3 = idx1 - w;
                        if (idx2 < sb.length())
                                sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);
                        displayR(sb, c + d, r + 2, d / 2, w, n.right, "\\ ");
                }
        }


}


class AvlTreeRotation {

        static AvlTreeNode leftRotation(AvlTreeNode oldRoot) {
                System.out.println("\nPerforming LEFT Rotation on Roll Number: " + oldRoot.rollNumber);
                AvlTreeNode newRoot;
                newRoot = oldRoot.left;
                oldRoot.left = newRoot.right;
                newRoot.right = oldRoot;

                oldRoot.height = AvlTreeOperation.height(oldRoot);
                newRoot.height = AvlTreeOperation.height(newRoot);

                return newRoot;
        }

        static AvlTreeNode rightRotation(AvlTreeNode oldRoot) {
                System.out.println("\nPerforming RIGHT Rotation on Roll Number: " + oldRoot.rollNumber);
                AvlTreeNode newRoot;
                newRoot = oldRoot.right;
                oldRoot.right = newRoot.left;
                newRoot.left = oldRoot;

                oldRoot.height = AvlTreeOperation.height(oldRoot);
                newRoot.height = AvlTreeOperation.height(newRoot);

                return newRoot;

        }
}