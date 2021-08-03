package nuc.edu;

/**
 * @author 薛东
 * @date 2021/3/16 11:40
 */
public class CircleQueue<E> {
    private int front;
    private int size;
    private E[] elements;

    public static final int DEFAULT_CAPACITY = 10;

    public CircleQueue(){
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void enQueue(E element){
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }

    public E deQueue(){
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return frontElement;
    }

    public E front(){
        return elements[front];
    }

    // 扩容
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        // 新容量为旧容量的 1.5 倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) { // 旧数组中元素移到新数组
            newElements[i] = elements[index(i)];
        }
        System.out.println("从" + oldCapacity + "扩容到" + newCapacity);
        elements = newElements;
        front = 0; // 重置front
    }

    private int index(int index){
        index+=front;
        return index - ((index >= elements.length) ? elements.length : 0);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capcacity=").append(elements.length).append(" size=").append(size).append(" front=")
                .append(front).append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}
