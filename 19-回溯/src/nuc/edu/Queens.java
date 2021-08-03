package nuc.edu;

/**
 * @author 薛东
 * @date 2021/5/8 10:35
 */
public class Queens {
    public static void main(String[] args) {
        new Queens().placeQueens(4);
    }

    int[] cols;
    int ways;

    public void placeQueens(int n){
        if (n < 1){
           return;
        }
        cols = new int[n];
        place(0);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    public void place(int row){
        if (row == cols.length){
            ways++;
            show();
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (isVaild(row,col)){
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    public boolean isVaild(int row,int col){
        for (int i = 0; i < row; i++) {
            if (cols[i] == col){
                return false;
            }
            if (row - i == Math.abs(cols[i] - col)){
                return false;
            }
        }
        return true;
    }

    public void show(){
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col){
                    System.out.print("1 ");
                }else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }
}
