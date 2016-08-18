import java.util.Random;

class DummyAgent {
    Random rnd = new Random();

    private int roomProbability, directionChangeProbability;
    private int roomSizeMin, roomSizeMax;
    private Direction direction;
    private int x, y;
    private int border;

    public DummyAgent() {
        roomProbability = 4;
        directionChangeProbability = 4;
    }

    private void randomDirection() {
        int dir = rnd.nextInt(4);
        if (dir == 0) direction = Direction.UP;
        if (dir == 1) direction = Direction.DOWN;
        if (dir == 2) direction = Direction.LEFT;
        if (dir == 3) direction = Direction.RIGHT;
    }

    public boolean canDig() {
        if (direction == Direction.UP && y - 1 < 0) return false;
        if (direction == Direction.DOWN && y + 1 == border) return false;
        if (direction == Direction.LEFT && x - 1 < 0) return false;
        if (direction == Direction.RIGHT && x + 1 == border) return false;
        return true;
    }

    public void digCorridor(DungeonMap map) {
        if (direction == Direction.UP) {
            y--;
        }
        if (direction == Direction.DOWN) {
            y++;
        }
        if (direction == Direction.LEFT) {
            x--;
        }
        if (direction == Direction.RIGHT) {
            x++;
        }
        if (map.getPoint(x, y) != 2) map.setPoint(x, y, 1);
    }

    public void digRoom(DungeonMap map, int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                map.setPoint(i, j, 2);
            }
        }
    }

    public boolean isTimeToChangeDirection() {
        if (rnd.nextInt(100) > directionChangeProbability) {
            directionChangeProbability = directionChangeProbability + 5;
            return false;
        } else {
            directionChangeProbability = 0;
            return true;
        }
    }

    public boolean isTimeToCreateRoom() {
        if (rnd.nextInt(100) > roomProbability) {
            roomProbability = roomProbability + 5;
            return false;
        } else {
            roomProbability = 0;
            return true;
        }
    }

    public boolean isOnCertainPosition(DungeonMap map, int pos){
        return (map.getPoint(x,y)==pos);
    }

    public void digDungeon(DungeonMap map) {
        border = map.getSize();

        x = 2; //setting position of dummy agent
        y = 2;
        randomDirection();

        if (map.isRoomPossible(x-2, y-2, x+2, y+2)) digRoom(map, x-2, y-2, x+2, y+2);
        while (map.getFullness() < 0.2) {
            while (!canDig()) {
                randomDirection();
            }
            digCorridor(map);
            if (isTimeToChangeDirection() && !isOnCertainPosition(map, 2)) randomDirection();
            if (isTimeToCreateRoom() && map.isRoomPossible(x-1, y-1, x+1, y+1)) digRoom(map, x-1, y-1, x+1, y+1);
        }
    }
}
