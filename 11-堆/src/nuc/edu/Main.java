package nuc.edu;

import nuc.edu.heap.BinaryHeap;
import nuc.edu.printer.BinaryTrees;

/**
 * @author 薛东
 * @date 2021/4/15 9:29
 */
public class Main {
    public static void main(String[] args) {
        test01();
    }

    static void test01(){
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);
        BinaryTrees.println(heap);
        /*heap.remove();
        BinaryTrees.println(heap);*/

        System.out.println(heap.replace(70));
        BinaryTrees.println(heap);
    }
}
