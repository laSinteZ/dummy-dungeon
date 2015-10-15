class DungeonMap {
    private int[][] map;
    private int size;

    public DungeonMap(int size){
        map = new int[size][size];
        this.size=size;
    }

    public int[][] getMap() {
        return map;
    }

    public int getSize() {
        return size;
    }

    public int getPoint(int x, int y){
        return map[y][x];
    }

    public void setPoint(int x, int y, int value){
        map[y][x]=value;
    }

    public double getFullness(){
        int notempty = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j]!=0) notempty=notempty+1;
            }
        }
        return (double)notempty/(size*size);
    }

    public void printDungeon(){
        System.out.println("\n--------");
        for (int i = 0; i < size; i++) {
            System.out.println();
            for (int j = 0; j < size; j++) {
                if (map[i][j]==0)System.out.print("  ");
                if (map[i][j]==1)System.out.print("1 ");

            }
        }
    }
}

