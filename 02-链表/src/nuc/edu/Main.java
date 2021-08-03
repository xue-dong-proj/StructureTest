package nuc.edu;

/**
 * @author 薛东
 * @date 2021/3/11 9:09
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(20);
        list.add(0,10);
        list.add(30);
        list.add(list.size(),40);

        list.remove(1);
        System.out.println(list);
    }
}
