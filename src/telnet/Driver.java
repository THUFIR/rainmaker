package telnet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Observable;
import java.util.Observer;
import weather.GameAction;

public class Driver implements Observer {

    private TelnetConnection telnetConnection = new TelnetConnection();
    private InputOutput inputOutput = new InputOutput();
    private Deque<GameAction> gameActions = new ArrayDeque<>();

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
    }

    private void sendAction(GameAction action) throws IOException {
        byte[] actionBytes = action.getAction().getBytes();
        OutputStream outputStream = telnetConnection.getOutputStream();
        outputStream.write(actionBytes);
        outputStream.write(13);
        outputStream.write(10);
        outputStream.flush();
    }

    private void sendActions() {
        while (!gameActions.isEmpty()) {
            GameAction action = gameActions.remove();
            try {
                sendAction(action);
            } catch (IOException ex) {
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof InputOutput) {
            gameActions = inputOutput.getGameActions();
            inputOutput.reset();
        }
    }
}
