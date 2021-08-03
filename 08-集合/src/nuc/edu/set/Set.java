package nuc.edu.set;

/**
 * @author 薛东
 * @date 2021/4/3 11:03
 */
public interface Set<E> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(E element);
    void add(E element);
    void remove(E element);
    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E>{
        boolean stop;
        public abstract boolean visit(E element);
    }
}
