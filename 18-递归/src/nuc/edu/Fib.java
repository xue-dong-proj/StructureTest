package nuc.edu;

import java.util.Map;

/**
 * @author 薛东
 * @date 2021/5/7 11:45
 */
public class Fib {
    public static void main(String[] args) {
        Fib fib = new Fib();
        int n = 10;
        Times.test("fib01",()->{
            System.out.println(fib.fib01(n));
        });

        Times.test("fib02",()->{
            System.out.println(fib.fib02(n));
        });

        Times.test("fib03",()->{
            System.out.println(fib.fib03(n));
        });

        Times.test("fib04",()->{
            System.out.println(fib.fib04(n));
        });

        Times.test("fib05",()->{
            System.out.println(fib.fib05(n));
        });

        Times.test("fib06",()->{
            System.out.println(fib.fib06(n));
        });

        Times.test("fib07",()->{
            System.out.println(fib.fib07(n));
        });
    }

    public int fib01(int n){
        if (n <= 2){
            return 1;
        }
        return fib01(n - 1) + fib01( n - 2);
    }

    public int fib02(int n){
        if (n <= 2){
            return 1;
        }
        int[] arr = new int[n + 1];
        arr[2] = arr[1] = 1;
        return fib02(n,arr);
    }

    public int fib02(int n,int[] arr){
        if (arr[n] == 0){
            arr[n] = fib02(n - 1,arr) + fib02(n - 2,arr);
        }
        return arr[n];
    }

    public int fib03(int n){
        if (n <= 2){
            return 1;
        }
        int[] arr = new int[n + 1];
        arr[2] = arr[1] = 1;
        for (int i = 3; i <= n; i++){
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    public int fib04(int n){
        if (n <= 2){
            return 1;
        }
        int[] arr = new int[2];
        arr[1] = arr[0] = 1;
        for (int i = 3; i <= n; i++){
            arr[i % 2] = arr[(i - 1) % 2] + arr[(i - 2) % 2];
        }
        return arr[n % 2];
    }

    public int fib05(int n){
        if (n <= 2){
            return 1;
        }
        int[] arr = new int[2];
        arr[1] = arr[0] = 1;
        for (int i = 3; i <= n; i++){
            arr[i & 1] = arr[(i - 1) & 1] + arr[(i - 2) & 1];
        }
        return arr[n & 1];
    }

    public int fib06(int n){
        double c = Math.sqrt(5);
        return (int)((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2,n)) / c);
    }

    public int fib07(int n){
        if (n <= 2){
            return 1;
        }
        int first = 1;
        int second = 1;
        for (int i = 3; i <= n; i++){
            second = first + second;
            first = second - first;
        }
        return second;
    }
}
