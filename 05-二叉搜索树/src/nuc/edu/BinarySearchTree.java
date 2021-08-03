package nuc.edu;

import nuc.edu.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 薛东
 * @date 2021/3/19 13:45
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    Node<E> root;

    private Comparator<E> comparator;

    public BinarySearchTree(){
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator){
        this.comparator = comparator;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){

    }

    public void add(E element){
        NodeNotNUllCheck(element);

        // 传入第一个节点
        if(root == null){
            root = new Node<>(element, null);
            size++;
            return;
        }
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        while(node != null){
            parent = node; // 父节点
            cmp = compareTo(node.element, element); // 方向
            if(cmp < 0){
                node = node.right;
            }else if(cmp > 0){
                node = node.left;
            }else{ // 相等，最好是覆盖掉
                node.element = element;
                return;
            }
        }
        Node<E> newNode = new Node<>(element, parent);
        if(cmp < 0){
            parent.right = newNode;
        }else{
            parent.left = newNode;
        }
        size++;

    }

    /**
     * 删除节点
     * @param element
     */
    public void remove(E element){
        remove(node(element));
    }

    private void remove(Node<E> node){
        if(root == null){
            return;
        }

        size--;

        // 如果度为2
        if(node.hasTowChildren()){
            // 找到它的前驱结点
            Node<E> pre = predecessor(node);
            node.element = pre.element;
            node = pre;
        }

        // 度为1的节点，它只存在左子节点，或者右子节点，如果左子节点不为空，那replacement就是node.left，反之，就是node.right
        Node<E> replacement = node.left != null ? node.left : node.right;

        // 如果replacement不为空的话，那么node肯定是度为1的节点
        if(replacement != null){   // node为度为1的节点
            replacement.parent = node.parent;

            if(node.parent == null){ //如果node的父节点为null，也就是node为根节点
                root = replacement;
            }else if (node == node.parent.left){  // 如果是左子节点的话，就让父节点的左子树指向自己的子节点
                node.parent.left = replacement;
            }else if(node == node.parent.right){  // 如果是右子节点的话，就让父节点的左子树指向自己的子节点
                node.parent.right = replacement;
            }
        }else if(node.parent == null){   // 如果只有一个根节点的话，那么删除操作就是让它的根节点指向null即可
            root = null;
        }else {  // node是叶子节点
            if(node == node.parent.right){  // 如果这个叶子节点是父子节点的右子节点
                node.parent.right = null;   // 直接将它置为null
            }else {
                // 如果这个叶子节点是父子节点的左子节点
                // 直接将它置为null
                node.parent.left = null;
            }
        }
    }

    private Node<E> node(E element){
        Node<E> node = root;
        int cmp = compareTo(element, node.element);
        while (node != null){
            if(cmp == 0){
                return node;
            }else if (cmp < 0){
                node = node.left;
            }else {
                node = node.right;
            }
        }

        return null;
    }

    public boolean contains(E element){
        return false;
    }

    /**
     * 前序遍历
     *//*
    public void preorderTraversal(){
        preorderTraversal(root);
    }

    *//**
     * 前序遍历
     *//*
    private void preorderTraversal(Node<E> node){
        if(node == null) {
            return;
        }

        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    *//**
     * 中序遍历
     *//*
    public void inorderTraversal(){
        inorderTraversal(root);
    }

    *//**
     * 中序遍历
     *//*
    private void inorderTraversal(Node<E> node){
        if(node == null){
            return;
        }

        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    *//**
     * 后序遍历
     *//*
    public void postorderTraversal(){
        postorderTraversal(root);
    }

    *//**
     * 后序遍历
     *//*
    private void postorderTraversal(Node<E> node){
        if(node == null){
            return;
        }

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    *//**
     * 层序遍历
     *//*
    public void levelOrderTraversal(){
        if(root == null){
            return;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            System.out.println(node.element);

            if(node.left != null){
                queue.offer(node.left);
            }

            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }*/

    /**
     * 前序遍历
     */
    public void preOrder(Visitor<E> visitor){
        if(visitor == null) {
            return;
        }
        preOrder(root,visitor);
    }

    /**
     * 前序遍历
     */
    private void preOrder(Node<E> node,Visitor<E> visitor){
        if(node == null || visitor.stop) {
            return;
        }

        visitor.stop = visitor.visit(node.element);
        preOrder(node.left,visitor);
        preOrder(node.right,visitor);
    }

    /**
     * 中序遍历
     */
    public void inOrder(Visitor<E> visitor){
        if(visitor == null){
            return;
        }
        inOrder(root,visitor);
    }

    /**
     * 中序遍历
     */
    private void inOrder(Node<E> node,Visitor<E> visitor){
        if(node == null || visitor.stop){
            return;
        }

        inOrder(node.left,visitor);
        if (visitor.stop){
            return;
        }
        visitor.stop = visitor.visit(node.element);
        inOrder(node.right,visitor);
    }

    /**
     * 后序遍历
     */
    public void postOrder(Visitor<E> visitor){
        if(visitor.stop){
            return;
        }
        postOrder(root,visitor);
    }

    /**
     * 后序遍历
     */
    private void postOrder(Node<E> node,Visitor<E> visitor){
        if(node == null || visitor.stop){
            return;
        }

        postOrder(node.left,visitor);
        postOrder(node.right,visitor);
        if(visitor.stop){
            return;
        }
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层序遍历
     */
    public void levelOrder(Visitor<E> visitor){
        if(root == null || visitor == null){
            return;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)){
                return;
            }

            if(node.left != null){
                queue.offer(node.left);
            }

            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }

    /**
     * 二叉树的高度，迭代方法
     * @return 二叉树的高度
     */
    public int high(){
        if(root == null){
            return 0;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        // 记录二叉树的高度
        int height = 0;
        // 记录二叉树每一层的节点个数
        int levelSize = 1;

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            levelSize--;
            if(node.left != null){
                queue.offer(node.left);
            }

            if(node.right != null){
                queue.offer(node.right);
            }

            if(levelSize == 0){
                levelSize = queue.size();
                height++;
            }
        }

        return height;
    }

    /**
     * 二叉树的高度，递归方式
     * @return
     */
    public int height(){
        return height(root);
    }

    /**
     * 二叉树的高度，递归方式
     * @return
     */
    private int height(Node<E> node){
        if(node == null){
            return 0;
        }

        return Math.max(height(node.left),height(node.right)) + 1;
    }

    /**
     * 判断是否为完全二叉树
     * @return
     */
    public boolean isComplete(){
        if(root == null){
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();

            if(leaf && !node.isLeaf()){
                return false;
            }

            if(node.left != null){
                queue.offer(node.left);
            }else if(node.right != null){
                return false;
            }

            if(node.right != null){
                queue.offer(node.right);
            }else {
                leaf = true;
            }
        }

        return true;
    }

    /**
     * 判断是不是完全二叉树
     * @return
     *//*
    public boolean isComplete(){
        if(root == null){
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();

            if(leaf && !node.isLeaf()){
                return false;
            }

            if(node.hasTowChildren()){
                queue.offer(node.left);
                queue.offer(node.right);
            }else if(node.left == null && node.right != null){
                return false;
            }else {
                leaf = true;
                if(node.left != null){
                    queue.offer(node.left);
                }
            }
        }

        return true;
    }*/

    /**
     * 寻找前驱结点
     * @param node
     * @return 前驱结点
     */
    private Node<E> predecessor(Node<E> node){
        if(node == null){
            return null;
        }

        Node<E> pre = node.left;
        if(pre != null){
            while (pre.right != null){
                pre = pre.right;
            }

            return pre;
        }

        while (node.parent != null && node == node.parent.left){
            node = node.parent;
        }

        return node.parent;
    }

    /**
     * 寻找后继结点
     * @param node
     * @return 后继结点
     */
    private Node<E> successor(Node<E> node){
        if(node == null){
            return null;
        }

        Node<E> suc = node.right;
        if(suc != null){
            while (suc.left != null){
                suc = suc.left;
            }

            return suc;
        }

        while (node.parent != null && node == node.parent.right){
            node = node.parent;
        }

        return node.parent;
    }

    public static abstract class Visitor<E>{
        boolean stop;
        abstract boolean visit(E element);
    }

    private void NodeNotNUllCheck(E element){
        if(element == null){
            throw new IllegalArgumentException("node not be null");
        }

    }

    private int compareTo(E e1,E e2){
        if (comparator != null) { // 传入比较器则通过比较器比较
            comparator.compare(e1, e2);
        }
        // 没传比较器，元素内部必须自行实现了 Comparable 接口
        return ((Comparable<E>)e1).compareTo(e2);
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";

        if(myNode.parent != null){
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }

    private static class Node<E>{
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element,Node<E> parent){
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }

        public boolean hasTowChildren(){
            return left != null && right != null;
        }
    }
}
