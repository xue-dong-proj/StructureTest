package nuc.edu;

/**
 * @author 薛东
 * @date 2021/3/6 9:22
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            arrayList.add(i);
        }

        for (int i = 0; i < 50; i++) {
            arrayList.remove(0);
        }

        System.out.println(arrayList);
    }
}
