import java.util.Scanner;

public class Main {

    private static int[][] intense;
    private static int W;
    private static int H;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        W = in.nextInt();
        H = in.nextInt();
        intense = new int[H][W];
        int row_min, row_max, col_min, col_max;
        //int rows = H/2, columns = W/2;
        int rows = H, columns = W;

        long time = System.currentTimeMillis();


        for (row_min = 0; row_min < rows; row_min++) {
            for (row_max = row_min; row_max < rows; row_max++) {
                for (col_min = 0; col_min < columns; col_min++) {
                    for (col_max = col_min; col_max < columns; col_max++) {
                        increaseRectangle(row_min, col_min, row_max, col_max);
                    }
                }
            }
        }


        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                System.out.print(intense[i][j]+" ");
            }
            System.out.println();
        }

        //попытка заполнить только 2-ую четверть
        /*int k = rows, m;
        for(int i = rows+1; i <= rows*2+1; i++) {
            m=columns;
            for(int j = columns+1; j <= columns*2+1; j++) {
                intense[i][j] = intense[k][m];
                m--;
            }
            k--;
        }

        Collections.reverse(Arrays.asList(intense));*/

        for (int i = 0; i < H; i++) {
            System.out.print(sumOfRow(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < W; i++) {
            System.out.print(sumOfColumn(i) + " ");
        }
        System.out.println();
        System.out.println((System.currentTimeMillis() - time)*0.001);

    }

    private static void increaseRectangle(int fromRow, int fromColumn, int toRow, int toColumn) {
        for (int i = fromRow; i <= toRow; i++) {
            for (int j = fromColumn; j <= toColumn; j++) {
                intense[i][j]++;
            }

            //попытка перехода к сингллайн массиву (из-за нескольких определений внутри аж 5 цикла время увеличилось)
            /*int[] singleLine = intense[i];
            for (int j = fromColumn; j <= toColumn; j++) {
                singleLine[j]++;
            }
            intense[i] = singleLine;*/
        }
    }

    private static int sumOfRow(int rowIndex) {
        int sum = 0;
        int[] row = intense[rowIndex];
        for (int value : row) {
            sum += value;
        }
        return sum;
    }

    private static int sumOfColumn(int columnIndex) {
        int sum = 0;
        for (int i = 0; i < H; i++) {
            sum += intense[i][columnIndex];
        }
        return sum;
    }
}
