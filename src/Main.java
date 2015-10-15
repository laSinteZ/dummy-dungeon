public class Main {
    public static void main(String args[]){
        DungeonMap dung = new DungeonMap(15);
        DummyAgent agent = new DummyAgent();
        agent.digDungeon(dung);
        dung.printDungeon();
    }
}

