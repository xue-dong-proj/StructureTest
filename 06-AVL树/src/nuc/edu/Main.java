package nuc.edu;

import nuc.edu.printer.BinaryTrees;
import nuc.edu.tree.AVLTree;

public class Main {
    // Integer类型的数据
    public static void test1(){
        Integer date[] = new Integer[] {
                75, 94, 21, 7, 93, 31, 83, 65, 43, 50, 57, 56
        };
        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < date.length; i++) {
            avl.add(date[i]);
            System.out.println("【" + date[i] + "】");
            BinaryTrees.println(avl);
            System.out.println("-----------------------------------------");
        }
    }

    public static void main(String[] args) {
        test1();
    }
}
