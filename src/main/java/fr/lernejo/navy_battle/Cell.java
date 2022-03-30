package fr.lernejo.navy_battle;

public class Cell {

    public final Integer[][] Sea = new Integer[10][10];
    Cell() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0 && i <= 4) { this.Sea[i][j] = 1; }
                else if (i >= 6 && j == 3 ) { this.Sea[i][j] = 2; }
                else if (i == 2 && (j == 8 || j == 9)) { this.Sea[i][j] = 4; }
                else if (i <= 2 && j == 6 ) { this.Sea[i][j] = 3;}
                else if (i == 5 && (j >= 5 && j <= 7)) { this.Sea[i][j] = 3;}
                else { this.Sea[i][j] = 0; }
            }
        }
    }
}
