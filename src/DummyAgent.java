import java.util.Random;

class DummyAgent{
    Random rnd = new Random();

    private int roomProbability, directionChangeProbability;
    private int roomSizeMin, roomSizeMax;
    private Direction direction;
    private int x, y;
    private int border;

    public DummyAgent(){
        roomProbability=4;
        directionChangeProbability=4;
    }

    private void randomDirection(){
        int dir = rnd.nextInt(4);
        if (dir==0) direction=Direction.UP;
        if (dir==1) direction=Direction.DOWN;
        if (dir==2) direction=Direction.LEFT;
        if (dir==3) direction=Direction.RIGHT;
    }

    public boolean canDig(){
        if (direction==Direction.UP && y-1<0) return false;
        if (direction==Direction.DOWN && y+1==border) return false;
        if (direction==Direction.LEFT && x-1<0) return false;
        if (direction==Direction.RIGHT && x+1==border) return false;
        return true;
    }

    public void digCorridor(DungeonMap map){
        if (direction==Direction.UP){
            y--;
            map.setPoint(x,y,1);
        }
        if (direction==Direction.DOWN){
            y++;
            map.setPoint(x,y,1);
        }
        if (direction==Direction.LEFT){
            x--;
            map.setPoint(x,y,1);
        }
        if (direction==Direction.RIGHT){
            x++;
            map.setPoint(x,y,1);
        }
    }

    public boolean isTimeToChangeDirection(){
        if (rnd.nextInt(100)>directionChangeProbability){
            directionChangeProbability=directionChangeProbability+5;
            return false;
        } else {directionChangeProbability=0;
        return true;}
    }

    public void digDungeon(DungeonMap map){
        border = map.getSize();

        x=rnd.nextInt(border); //setting position of
        y=rnd.nextInt(border); //dummy agent
        randomDirection();

        while (map.getFullness()<0.2){
            while (!canDig()){
                randomDirection();
            }
            digCorridor(map);
            if (isTimeToChangeDirection()) randomDirection();
        }
    }
}
