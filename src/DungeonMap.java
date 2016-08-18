class DungeonMap {
    private int[][] map;
    private int size;

    public DungeonMap(int size) {
        map = new int[size][size];
        this.size = size;
    }

    public int[][] getMap() {
        return map;
    }

    public int getSize() {
        return size;
    }

    public int getPoint(int x, int y) {
        return map[y][x];
    }

    public void setPoint(int x, int y, int value) {
        map[y][x] = value;
    }

    public double getFullness() {
        int notempty = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] != 0) notempty = notempty + 1;
            }
        }
        return (double) notempty / (size * size);
    }

    public boolean isRoomPossible(int x1, int y1, int x2, int y2) {
        if ((x1 < 0) || (x2 < 0) || (y1 < 0) || (y2 < 0) || (x1 >= size) || (x2 >= size) || (y1 >= size) || (y2 >= size))
            return false;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (map[i][j] == 2) return false;
            }
        }
        return true;
    }

    public void printDungeon() {
        System.out.println("\n--------");
        for (int i = 0; i < size; i++) {
            System.out.println();
            for (int j = 0; j < size; j++) {
                if (map[i][j] == 0) System.out.print("  ");
                if (map[i][j] == 1) System.out.print("1 ");
                if (map[i][j] == 2) System.out.print("2 ");

            }
        }
    }
}

