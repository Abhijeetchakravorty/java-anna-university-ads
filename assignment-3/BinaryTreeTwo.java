import java.util.*;
import java.io.*;
public class BinaryTreeTwo {
        public static String spacered(int n) {
                String t = "";
                for(int i=0;i<n;i++) {
                        t+="\t";        
                }
                return t;
        }
        public static void main(String args[]) {
                System.out.print("starting");
                System.out.print(spacered(5));
                System.out.print("Ending");
        }
}
