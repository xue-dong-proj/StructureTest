package nuc.edu;

import nuc.edu.printer.BinaryTrees;

/**
 * @author 薛东
 * @date 2021/3/19 15:39
 */
public class Main {
    public static void main(String[] args) {
        //test02();
//        test01();
        test03();
    }

    static void test01(){
        Integer data[] = new Integer[]{
                7,4,2,1,3,5,9,8,11,10,12
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        // System.out.println(bst.height());
        System.out.println(bst.high());
        //bst.levelOrderTraversal();
    }

    static void test02(){
        Integer data[] = new Integer[]{
                7,4,9,2,1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        bst.preOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 2 ? true : false;
            }
        });
        System.out.println();

        bst.inOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 4 ? true : false;
            }
        });
        System.out.println();

        bst.postOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 4 ? true : false;
            }
        });
        System.out.println();

        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 9 ? true : false;
            }
        });
    }

    static void test03(){
        Integer data[] = new Integer[]{
                7,4,9,2,5
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        // System.out.println(bst.height());
        System.out.println(bst.isComplete());
    }
}
