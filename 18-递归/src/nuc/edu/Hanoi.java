package nuc.edu;

/**
 * @author 薛东
 * @date 2021/5/7 18:22
 */
public class Hanoi {
    public static void main(String[] args) {
        hanio(3,"A","B","C");
    }

    public static void hanio(int n,String A,String B,String C){
        if (n == 1){
            move(n,A,C);
            return;
        }
        hanio(n - 1,A,C,B);
        move(n,A,C);
        hanio(n - 1,B,A,C);
    }

    /**
     * 将 no 号盘子从 from 移动到 to
     * @param no
     * @param from
     * @param to
     */
    public static void move(int no, String from, String to) {
        System.out.println("将" + no + "号盘子从" + from + "移动到" + to);
    }
}
