package nuc.edu;

import java.util.Arrays;

/**
 * @author 薛东
 * @date 2021/3/5 16:43
 */
public class ArrayList<E> {
    /**
     * 数组的大小
     */
    private int size;

    /**
     * 所有元素
     */
    private E[] elements;

    public static final int DEFAULT_CAPACITY = 10;
    public static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capacity){
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[])new Object[capacity];
    }

    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清除所有元素
     */
    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
    }

    /**
     * 元素的数量
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 是否包含某个元素
     * @param element
     * @return
     */
    public boolean contains(E element){
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素到尾部
     * @param element
     */
    public void add(E element){
        add(size,element);
    }

    /**
     * 获取index位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        rangeCheck(index);

        return elements[index];
    }

    /**
     * 设置index位置的元素
     * @param index
     * @param element
     * @return 原来的元素
     */
    public E set(int index, E element){
        rangeCheck(index);

        E old = elements[index];
        elements[index] = element;

        return old;
    }

    /**
     * 在index的位置插入一个元素
     * @param index
     * @param element
     */
    public void add(int index, E element){
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);

        for(int i = size - 1; i >= index; i--){
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 删除index位置的元素
     * @param index
     * @return
     */
    public E remove(int index){
        rangeCheck(index);

        E old = elements[index];

        for(int i = index + 1; i <= size - 1; i++){
            elements[i-1] = elements[i];
        }

        size--;
        elements[size] = null;

        trim();
        return old;
    }

    /**
     * 查看元素的索引
     * @param element
     * @return
     */
    public int indexOf(E element){
        if(element == null){
            for (int i = 0; i < size; i++) {
                if(elements[i] == null) {
                    return i;
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                if(element.equals(elements[i])) {
                    return i;
                }
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity >= capacity){
            return;
        }

        // 扩容为原来的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
        System.out.println(oldCapacity + "扩容为：" + newCapacity);
    }

    private void trim(){
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity >> 1;
        if(size >= (oldCapacity >> 1) || oldCapacity <= DEFAULT_CAPACITY){
            return;
        }

        E[] newElements = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
        System.out.println(oldCapacity + "缩容为：" + newCapacity);
    }

    private void outOfBounds(int index){
        throw new IndexOutOfBoundsException("index:" + index + ",size:" + size);
    }

    private void rangeCheck(int index){
        if(index < 0 || index >= size){
            outOfBounds(index);
        }
    }

    private void rangeCheckForAdd(int index){
        if(index < 0 || index > size){
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if(i != 0){
                string.append(", ");
            }
            string.append(elements[i]);
        }

        string.append("]");

        return string.toString();
    }
}