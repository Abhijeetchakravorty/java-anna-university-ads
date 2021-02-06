import java.util.*;
public class MinHeap {

        int heapArray[];
        final int MAX_SIZE;
        int size;

        MinHeap(int MAX_SIZE) {
                this.MAX_SIZE = MAX_SIZE;
                heapArray = new int[MAX_SIZE];
                size = 0;
        }

        public static void main(String[] args) {
                Scanner input = new Scanner(System.in);
                System.out.printf("Enter Maximum Size of Heap: ");
                final int MAX_SIZE = input.nextInt();
                MinHeap heap = new MinHeap(MAX_SIZE);
                int data;
                while (true) {

                        System.out.println("\nOptions:");
                        System.out.println("1. Insert Into Heap");
                        System.out.println("2. Delete from Heap");
                        System.out.println("3. Print Heap");
                        System.out.println("4. Exit");
                        System.out.printf("Please Enter any option from above list: ");
                        int choice = input.nextInt();

                        switch (choice) {
                                case 1:
                                        if (heap.size == heap.MAX_SIZE) {
                                                System.out.println("HEAP is FULL. Insertion isn't possible");
                                                break;
                                        }
                                        System.out.printf("Enter data to insert into heap: ");
                                        data = input.nextInt();
                                        heap = MinHeapOperation.insert(heap, data);
                                        MinHeapOperation.display(heap);
                                        break;
                                case 2:
                                        System.out.printf("Enter data to delete from heap: ");
                                        data = input.nextInt();
                                        heap = MinHeapOperation.delete(heap, data);
                                        MinHeapOperation.display(heap);
                                        break;
                                case 3:
                                        System.out.println("Heap: ");
                                        MinHeapOperation.display(heap);
                                        break;
                                case 4:
                                        System.exit(0);
                                default:
                                        System.out.println("Oops!! Invalid Option. Please select valid option");
                                        break;
                        }

                }
        }
}

class MinHeapOperation {

        static MinHeap insert(MinHeap heap, int data) {

                heap.heapArray[heap.size] = data;
                heap.size += 1;
                int size = heap.size - 1;
                while (size != 0) {
                        int parent = (size - 1) / 2;
                        if (heap.heapArray[size] < heap.heapArray[parent]) {
                                heap.heapArray[size] += heap.heapArray[parent];
                                heap.heapArray[parent] = heap.heapArray[size] - heap.heapArray[parent];
                                heap.heapArray[size] -= heap.heapArray[parent];
                                size = parent;
                        } else
                                break;
                }
                return heap;
        }

        static int[] removeTheElement(int[] arr, int index) {
		int[] my_array = arr;
		int removeIndex = index;

		for(int i = removeIndex; i < my_array.length -1; i++){
			my_array[i] = my_array[i + 1];
		}

		return my_array;
        }

        static MinHeap delete(MinHeap heap, int data) {
		int j = 0, index = 0;
		int arrs[] = new int[heap.size-1];
                while (j < heap.heapArray.length) {
			if (heap.heapArray[j]==data) {
				index = j;
				break;
			}
                        j++;
		}
	
		heap.heapArray = removeTheElement(heap.heapArray, index);
                int temp = heap.heapArray[0];
                heap.heapArray[heap.size-1] = temp;
		heap.size -= 1;
		
                return heap;
        }

        static void display(MinHeap heap) {
                int height, width;
                height = (int)(Math.log(heap.size) / Math.log(2)) + 1;
                width = (int) Math.ceil(Math.pow(2, height + 2));
                int len = width * height * 2 + 2;
                StringBuilder sb = new StringBuilder(len);
                for (int i = 1; i <= len; i++)
                        sb.append(i < len - 2 && i % width == 0 ? "\n" : ' ');

                displayR(sb, width / 2, 1, width / 4, width, heap.heapArray, 0, " ", heap.size);
                System.out.println(sb);
        }

        static void displayR(StringBuilder sb, int c, int r, int d, int w, int[] heap, int n,
                String edge, int size) {
                if (n < size) {
                        displayR(sb, c - d, r + 2, d / 2, w, heap, n * 2 + 1, " /", size);
                        String s = String.valueOf(heap[n]);
                        int idx1 = r * w + c - (s.length() + 1) / 2;
                        int idx2 = idx1 + s.length();
                        int idx3 = idx1 - w;
                        if (idx2 < sb.length())
                                sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);
                        displayR(sb, c + d, r + 2, d / 2, w, heap, n * 2 + 2, "\\ ", size);
                }
        }
}