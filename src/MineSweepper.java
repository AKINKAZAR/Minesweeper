import java.util.Random;
import java.util.Scanner;


public class MineSweepper {
    public Scanner scan = new Scanner(System.in);
    int rowNumber;
    int colNumber;

    public MineSweepper(int rowNumber, int colNumber) {
        this.colNumber = colNumber;
        this.rowNumber = rowNumber;
    }

    public boolean isGameOver(String[][] list, String empty) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                if (list[i][j].equals(empty)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void listDuplicate(String[][] list, String[][] duplicateList) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                duplicateList[i][j] = list[i][j];
            }
        }
    }

    public void showListWithEmpty(String[][] list, String empty) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                if (list[i][j] == null) {
                    list[i][j] = empty;
                }
                System.out.print(list[i][j]);
            }
            System.out.println();
        }
    }

    public void putMine(String list[][], int listSize, String mine) {
        Random rand = new Random();
        for (int i = 0; i < (listSize / 4); i++) {
            int row = rand.nextInt(rowNumber);
            int col = rand.nextInt(colNumber);

            if (list[row][col] == null) {
                list[row][col] = mine;
            } else {
                i--;
            }
        }
    }

    public void run() {
        String[][] list = new String[rowNumber][colNumber];
        String[][] duplicateList = new String[rowNumber][colNumber];
        int listSize = rowNumber * colNumber;
        String mine = "* ";
        String empty = "- ";
        boolean isWin = false;
        boolean isRowInRange = true;
        boolean isColInRange = true;
        int selectRow, selectCol;

        listDuplicate(list, duplicateList);
        putMine(list, listSize, mine);
        System.out.println("Mayınların Konumu");
        showListWithEmpty(list, empty);
        System.out.println("=========================");
        System.out.println("Mayın Tarlası Oyununa Hoş Geldiniz.");

        while (!isWin) {
            showListWithEmpty(duplicateList, empty);
            if (isGameOver(list, empty)) {
                System.out.println("Oyunu Kazandınız !");
                break;
            }
            do {
                System.out.print("Satır Giriniz: ");
                selectRow = scan.nextInt();
                if (selectRow < 0 || selectRow > list.length - 1) {
                    System.out.println("Girilen değer 0 ile " + (list.length - 1) + " arasında olmalıdır.");
                } else {
                    isRowInRange = false;
                }
            } while (isRowInRange);
            do {
                System.out.print("Sütun Giriniz: ");
                selectCol = scan.nextInt();
                if (selectCol < 0 || selectCol > list[selectRow].length - 1) {
                    System.out.println("Girilen değer 0 ile " + (list.length - 1) + " arasında olmalıdır.");
                } else {
                    isColInRange = false;
                }
            } while (isColInRange);

            System.out.println("=========================");

            if (list[selectRow][selectCol].equals(mine)) {
                isWin = true;
                showListWithEmpty(list, empty);
                System.out.println("Game Over!!");
            } else {
                int count = 0;
                if (selectRow == 0) {
                    for (int i = 0; i < 2; i++) {
                        if (selectCol == 0) {    // sol üst
                            for (int j = 0; j < 2; j++) {
                                if (list[i][j].equals(mine)) {
                                    count++;
                                }
                            }
                        } else if (selectCol == list.length - 1) {   //sağ üst
                            for (int j = list[i].length - 1; j > list[i].length - 3; j--) {
                                if (list[i][j].equals(mine)) {
                                    count++;
                                }
                            }
                        } else {  //Üst orta
                            for (int j = selectCol - 1; j < selectCol + 2; j++) {
                                if (list[i][j].equals(mine)) {
                                    count++;
                                }
                            }
                        }
                    }
                } else if (selectRow == list.length - 1) {
                    for (int i = list.length - 1; i > list.length - 3; i--) {
                        if (selectCol == 0) {                                         //sol alt
                            for (int j = 0; j < 2; j++) {
                                if (list[i][j].equals(mine)) {
                                    count++;
                                }
                            }
                        } else if (selectCol == list[i].length - 1) {                 //sağ alt
                            for (int j = list[i].length - 1; j > list[i].length - 3; j--) {
                                if (list[i][j].equals(mine)) {
                                    count++;
                                }
                            }
                        } else {                                                        // alt orta
                            for (int j = selectCol - 1; j < selectCol + 2; j++) {
                                if (list[i][j].equals(mine)) {
                                    count++;
                                }
                            }
                        }
                    }
                }

                if (selectRow != 0 && selectRow != list.length - 1) {    // sol orta
                    for (int i = selectRow - 1; i < selectRow + 2; i++) {
                        if (selectCol == 0) {
                            for (int j = 0; j < 2; j++) {
                                if (list[i][j].equals(mine)) {
                                    count++;
                                }
                            }
                        } else if (selectCol == list[i].length - 1) {
                            for (int j = list[i].length - 1; j > list[i].length - 3; j--) {
                                if (list[i][j].equals(mine)) {
                                    count++;
                                }
                            }
                        }
                    }
                }

                if ((selectRow != 0 && selectRow != list.length - 1) && selectCol != 0 && selectCol != list[selectCol].length - 1) {
                    for (int i = selectRow - 1; i < selectRow + 2; i++) {
                        for (int j = selectCol - 1; j < selectCol + 2; j++) {
                            if (list[i][j].equals(mine)) {
                                count++;
                            }
                        }
                    }
                }

                duplicateList[selectRow][selectCol] = count + " ";
                list[selectRow][selectCol] = count + " ";
            }

        }
    }
}

