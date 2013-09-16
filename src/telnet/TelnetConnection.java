package telnet;



import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Observable;
import java.util.Observer;
import org.apache.commons.net.telnet.TelnetClient;
import weather.GameAction;

public class TelnetConnection implements Observer {

    private TelnetClient telnetClient = new TelnetClient();
    private InputOutput inputOutput = new InputOutput();
    private Deque<GameAction> gameActions = new ArrayDeque<>();

    public static void main(String[] args) {
        new TelnetConnection();
    }

    public TelnetConnection() {
        init();
    }

    private void init() {
        try {
            int port = 3000;
            InetAddress host = InetAddress.getByName("rainmaker.wunderground.com");
            telnetClient.connect(host, port);
            inputOutput.readWriteParse(telnetClient.getInputStream(), telnetClient.getOutputStream());
        } catch (SocketException ex) {
        } catch (IOException ex) {
        }
        inputOutput.addObserver(this);
    }

    private void sendAction(GameAction action) throws IOException {
        byte[] actionBytes = action.getAction().getBytes();
        OutputStream outputStream = telnetClient.getOutputStream();
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
