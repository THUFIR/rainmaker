package telnet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Deque;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;
import player.GameAction;
import player.GameData;
import player.TelnetParser;

public class TelnetConnection implements Observer {

    private static Logger log = Logger.getLogger(TelnetConnection.class.getName());
    private TelnetClient telnetClient = new TelnetClient();
    private InputOutput inputOutput = new InputOutput();
    private TelnetParser parser = new TelnetParser();
    private Logic logic = new Logic();

    public TelnetConnection() {
        try {
            init();
        } catch (SocketException ex) {
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    private void init() throws SocketException, FileNotFoundException, IOException {
        Properties props = PropertiesReader.getProps();
        InetAddress host = InetAddress.getByName(props.getProperty("host"));
        int port = Integer.parseInt(props.getProperty("port"));
        telnetClient.connect(host, port);
        inputOutput.readWriteParse(telnetClient.getInputStream(), telnetClient.getOutputStream());
        inputOutput.addObserver(this);
        parser.addObserver(this);
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
        GameData data = null;
        String line = null;
        if (o instanceof InputOutput) {
            line = arg.toString();
            parser.parse(line);
        } else if (o instanceof TelnetParser) {
            log.fine("trying..");
            data = (GameData) arg;
            log.fine("hmm, never get here");
            log.fine(data.getEnemy());
            Deque<GameAction> gameActions = logic.getActions(data);
            sendActions(gameActions);
        }
    }

    public static void main(String[] args) {
        new TelnetConnection();
    }
}