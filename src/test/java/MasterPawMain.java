import com.codingame.gameengine.runner.MultiplayerGameRunner;

public class MasterPawMain {
    public static void main(String[] args) {
        MultiplayerGameRunner gameRunner = new MultiplayerGameRunner();

        gameRunner.addAgent(Agent1.class);
        gameRunner.addAgent(Agent2.class);

        gameRunner.start();
    }
}
