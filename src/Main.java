
public class Main {
    public static void main(String[] args) {

        System.out.println("Mayın Tarlasının Büyüklüğünü Belirtiniz:");
        MineSweepper mine = new MineSweepper(GameSizeSetting.GetGameRow(), GameSizeSetting.GetGameCol());
        mine.run();
    }
}
