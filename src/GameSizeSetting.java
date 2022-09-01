import java.util.Scanner;

public class GameSizeSetting {
    static Scanner scan = new Scanner(System.in);

    public static int GetGameRow() {
        System.out.print("Satır Boyutu: ");
        int row = scan.nextInt();
        return row;
    }

    public static int GetGameCol() {
        System.out.print("Sütun Boyutu: ");
        int col = scan.nextInt();
        return col;
    }
}
