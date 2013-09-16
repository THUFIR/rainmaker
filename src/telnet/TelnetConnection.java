package telnet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Deque;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;
import weather.GameAction;
import weather.Player;
import weather.Regex;

public class TelnetConnection implements Observer {

    private static Logger log = Logger.getLogger(TelnetConnection.class.getName());
    private TelnetClient telnetClient = new TelnetClient();
    private InputOutput inputOutput = new InputOutput();
    private Regex rx = new Regex();
    private Player player = null;
    private Logic logic = new Logic();

    public TelnetConnection() {
        init();
    }

    private void init() {
        try {
            int port = 6789;
            InetAddress host = InetAddress.getByName("dune.servint.com");
            telnetClient.connect(host, port);
            inputOutput.readWriteParse(telnetClient.getInputStream(), telnetClient.getOutputStream());
        } catch (SocketException ex) {
        } catch (IOException ex) {
        }
        inputOutput.addObserver(this);
    }

    private void sendAction(GameAction action) throws IOException {
        log.info(action.toString());
        byte[] actionBytes = action.getAction().getBytes();
        OutputStream outputStream = telnetClient.getOutputStream();
        outputStream.write(actionBytes);
        outputStream.write(13);
        outputStream.write(10);
        outputStream.flush();
    }

    private void sendActions(Deque<GameAction> gameActions) {
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
        String line = null;
        if (o instanceof InputOutput) {
            line = inputOutput.getLine();
            log.fine(line);
            player = rx.parse(line);
            Deque<GameAction> gameActions = logic.getActions(player);
            sendActions(gameActions);
        }
    }

    public static void main(String[] args) {
        new TelnetConnection();
    }

}