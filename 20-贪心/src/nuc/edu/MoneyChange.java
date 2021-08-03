package nuc.edu;

import java.util.Arrays;

/**
 * @author 薛东
 * @date 2021/5/8 17:27
 */
public class MoneyChange {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{25,10,5,1};
        int money = 41;
        moneyChange(arr,money);
    }

    public static void moneyChange(Integer[] arr,int money){
        Arrays.sort(arr, (Integer a1, Integer a2) -> a2 - a1);
        int count = 0;
        int i = 0;
        while (i < arr.length){
            if (money < arr[i]){
                i++;
                continue;
            }
            System.out.println(arr[i]);
            money -= arr[i];
            count++;
        }
        System.out.println("一共兑换的硬币数位：" + count);
    }
}
