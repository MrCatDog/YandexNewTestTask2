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
        int centerX = (int) (0.5 + H / 2.0), centerY = (int) (W / 2.0 + 0.5);
        int furthestDistanceFromCentre = euclideanDistance(0, 0, centerX, centerY);

        for (int i = 0; i < centerX; i++) {
            for (int j = 0; j < centerY; j++) {
                intense[i][j] = furthestDistanceFromCentre - euclideanDistance(i, j, centerX, centerY);//here you can add some number to increase color intense
            }
        }

        //Further code is optional. You can just run cycle one(both sizes are odd), two(one size odd) or four times (both sizes are even).
        int minus = W % 2 != 0 ? 2 : 1; //skip center line for odd and repeat for even

        int m;
        for (int i = 0; i < centerX; i++) {
            m = centerY - minus;
            for (int j = centerY; j < W; j++) {
                intense[i][j] = intense[i][m];
                m--;
            }
        }

        minus = H % 2 != 0 ? 2 : 1;
        m = centerX - minus;
        for (int i = centerX; i < H; i++) {
            for (int j = 0; j < W; j++) {
                intense[i][j] = intense[m][j];
            }
            m--;
        }

        for (int i = 0; i < H; i++) {
            System.out.print(sumOfRow(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < W; i++) {
            System.out.print(sumOfColumn(i) + " ");
        }
    }

    public static int euclideanDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.floor(
                Math.sqrt(
                        Math.pow(x1 - x2, 2) +
                                Math.pow(y1 - y2, 2)
                )
        );
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

    public static void showIntense() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(intense[i][j] + " ");
            }
            System.out.println();
        }
    }
}
