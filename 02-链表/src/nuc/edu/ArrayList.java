package nuc.edu;

/**
 * @author 薛东
 * @date 2021/3/5 16:43
 */
public class ArrayList<E> extends AbstractList<E>{

    private E[] elements; 	// 所有的元素

    private static final int DEFAULT_CAPACITY = 10; // 初始容量
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capacity) { // 容量小于10一律扩充为10
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[])new Object[capacity];
    }
    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }

    // 扩容
    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity >= capacity) {
            return;
        }
        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i]; // 拷贝原数组元素到新数组
        }
        elements = newElements;
        System.out.println("size="+oldCapacity+", 扩容到了"+newCapacity);
    }
    /****************↑封装好的功能函数，遇到再读*******************/
    /**
     * 元素的数量
     * @return
     */
    @Override
    public int size(){
        return size;
    }
    /**
     * 是否为空
     * @return
     */
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    /**
     * 是否包含某个元素
     * @param element
     * @return
     */
    @Override
    public boolean contains(E element){
        return indexOf(element) != ELEMENT_NOT_FOUND; //找的到该元素则返回True
    }
    /**
     * 在index位置插入一个元素
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element){
        rangeCheckForAdd(index); // 检查下标越界

        ensureCapacity(size + 1); // 确保容量够大

        // 0 1 2 3 4 5 6 7 8 9	(index)
        // 1 2 3 4 5 6 x x x x	(原数组)
        // 在index=2处，插入9，元素全部后移
        // 1 2 9 3 4 5 6 x x x	(add后数组)

		/*
		// 问题出哪了？
		for (int i = size-1; i > index; i--) {
			elements[i+1] = elements[i];
		}
		*/
		/*
		// 问题已经解决，上面的情况会少 copy 一个，下面是正解。
		for (int i = size-1; i >= index; i--) {
			elements[i+1] = elements[i];
		}
		*/
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }
//	/*
//	 * 添加元素到最后面
//	 */

    /**
     * 设置index位置的元素
     * @param index
     * @param element
     * @return 原来的元素ֵ
     */
    @Override
    public E get(int index){
        rangeCheck(index);
        return elements[index];
    }
    /**
     * 设置index位置的元素
     * @param index
     * @param element
     * @return 原来的元素ֵ
     */
    @Override
    public E set(int index, E element){
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }
    /**
     * 删除index位置的元素
     * @param index
     * @return
     */
    @Override
    public E remove(int index){
        rangeCheck(index);
        // 0 1 2 3 4 5 	(index)
        // 1 2 3 4 5 6 	(原数组)
        // 删除index为2的元素，元素前移
        // 1 2 4 5 6	(remove后的数组)
        E old = elements[index];
        for (int i = index; i < size-1; i++) {
            elements[i] = elements[i+1];
        }
        elements[--size] = null; // 删除元素后,将最后一位设置为null
        return old;
    }
    /**
     * 查看元素的索引
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element){
        if(element == null){ // 对 null 进行处理
            for (int i = 0; i < size; i++) {
                if(elements[i] == null) return i;
            }
        }else{
            for (int i = 0; i < size; i++) {
                if(element.equals(elements[i])) return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }
    /**
     * 清除所有元素
     */
    @Override
    public void clear(){
        // 使用泛型数组后要注意内存管理
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
    @Override
    public String toString() {
        // 打印形式为: size=5, [99, 88, 77, 66, 55]
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if(0 != i) string.append(", ");
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}