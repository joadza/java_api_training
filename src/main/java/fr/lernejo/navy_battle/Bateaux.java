package fr.lernejo.navy_battle;

public class Bateaux {

    public int B_check(Cell map, JsonFireHandlerProp JsonProp) {
        if (map.Sea[JsonProp.row][JsonProp.col] == 1 && B_Avion(map) == 0) {
            return 0;
        }
        else if (map.Sea[JsonProp.row][JsonProp.col] == 2 && B_Cruiser(map) == 0)
            return 0;
        else if (map.Sea[JsonProp.row][JsonProp.col] == 3 && B_Destroyers(map) == 0)
            return 0;
        else if (map.Sea[JsonProp.row][JsonProp.col] == 4 && B_Torpedo(map) == 0)
            return 0;
        return 1;
    }

    private int B_Torpedo(Cell map) {
        int Vie = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map.Sea[i][j] == 4)
                    Vie = Vie + 1;
            }
        }
        if (Vie == 1) {
            return 0;
        }
        return 1;
    }

    private int B_Destroyers(Cell map) {
        int Vie = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map.Sea[i][j] == 3) {
                    Vie = Vie + 1;
                }
            }
        }
        if (Vie == 1) {
            return 0;
        }
        else if (Vie == 4) {
            return B_D_Check(map);
        }
        return 1;
    }

    private int B_Avion(Cell map) {
        int Vie = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map.Sea[i][j] == 1)
                    Vie = Vie + 1;
            }
        }
        if (Vie == 1)
            return 0;
        return 1;
    }

    private int B_Cruiser(Cell map) {
        int Vie = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map.Sea[i][j] == 2)
                    Vie = Vie + 1;
            }
        }
        if (Vie == 1)
            return 0;
        return 1;
    }

    private int B_D_Check(Cell map) {
        for (int i = 0; i < 10; i++) { for (int j = 0; j < 10; j++) {
            if (map.Sea[i][j] == 3 && (j > 0 && j < 9 && (map.Sea[i][j - 1] != 3 || map.Sea[i][j + 1] != 3))) {
                if (i > 0 && i < 9 && (map.Sea[i - 1][j] != 3 || map.Sea[i + 1][j] != 3)) { return 0; }
                else if ((i == 0 && map.Sea[i + 1][j] != 3) || (i == 9 && map.Sea[i - 1][j] != 3)) { return 0; }
            }
            else if (map.Sea[i][j] == 3 && (j == 0 && map.Sea[i][j + 1] != 3) || (j == 9 && map.Sea[i][j - 1] != 3)) {
                if (i > 0 && i < 9 && (map.Sea[i - 1][j] != 3 || map.Sea[i + 1][j] != 3)) { return 0; }
                else if ((i == 0 && map.Sea[i + 1][j] != 3) || (i == 9 && map.Sea[i - 1][j] != 3)) { return 0; }
            }
        } }
        return 1;
    }


}
