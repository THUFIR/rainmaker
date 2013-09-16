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
import player.GameAction;
import player.DataFromRegex;
import player.TelnetParser;

public class TelnetConnection implements Observer {

    private static Logger log = Logger.getLogger(TelnetConnection.class.getName());
    private TelnetClient telnetClient = new TelnetClient();
    private InputOutput inputOutput = new InputOutput();
    private TelnetParser parser = new TelnetParser();
    private Logic logic = new Logic();

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
        DataFromRegex data = null;
        String line = null;
        if (o instanceof InputOutput) {
            line = arg.toString();
            parser.parse(line);
        } else if (o instanceof TelnetParser) {
            data = (DataFromRegex) arg;
            log.info("hmm");
            Deque<GameAction> gameActions = logic.getActions(data);
            sendActions(gameActions);
        }
    }

    public static void main(String[] args) {
        new TelnetConnection();
    }
}