package telnet;

import game.LogicalContext;
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
import model.GameAction;
import model.GameData;
import model.TelnetEventProcessor;

public class TelnetConnection implements Observer {

    private static Logger log = Logger.getLogger(TelnetConnection.class.getName());
    private TelnetClient telnetClient = new TelnetClient();
    private InputOutput inputOutput = new InputOutput();
    private TelnetEventProcessor parser = new TelnetEventProcessor();
    private LogicalContext logic;// = new LogicalContext();

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
        log.fine(action.toString());
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
        TargetStrategy aliasTarget = null;
        if (o instanceof InputOutput) {
            if (arg instanceof String) {
                line = arg.toString();
                parser.parse(line);
            } else if (arg instanceof TargetStrategy) {
                aliasTarget = (TargetStrategy) arg;
                log.fine("target\n" + aliasTarget.toString());
                //logic.setTarget(aliasTarget);
                logic = new LogicalContext(aliasTarget);
                logic.executeStrategy();
            } else {
                log.info("not a i/o arg");
            }
        } else if (o instanceof TelnetEventProcessor) {
            if (arg instanceof GameData) {
                log.info("game data arg");
                data = (GameData) arg;
                Deque<GameAction> gameActions = logic.getActions(data);
                sendActions(gameActions);
            } else {
                log.info("not a telnetevent arg");
            }
        }
    }

    public static void main(String[] args) {
        new TelnetConnection();
    }
}