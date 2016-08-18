import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws Exception {

        DungeonMap dung = new DungeonMap(20);
        DummyAgent agent = new DummyAgent();
        agent.digDungeon(dung);
        dung.printDungeon();

    }
}

