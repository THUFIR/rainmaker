package telnet;

import game.Context;
import game.RulesForStrategy;
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

public class TelnetConnection implements Observer {
    
    private static Logger log = Logger.getLogger(TelnetConnection.class.getName());
    private TelnetClient telnetClient = new TelnetClient();
    private InputOutput inputOutput = new InputOutput();
    private TelnetEventProcessor eventProcessor = new TelnetEventProcessor();
    private RulesForStrategy rules = null;
    private Context context = null;
    
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
//        eventProcessor.addObserver(this);
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
    
    private void newData(GameData data) {
        log.fine("new data?\t" + data + "\n end new data");
        if (data != null) {
            log.fine("not null data:\n" + data + "\nend new data");
            rules = new RulesForStrategy(data);
            context = rules.getContext();
            Deque<GameAction> gameActions = context.executeStrategy();
            while (!gameActions.isEmpty()) {
                GameAction action = gameActions.remove();
                try {
                    sendAction(action);
                } catch (IOException ex) {
                }
            }
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        GameData data = null;
        String line = null;
        if (o instanceof InputOutput) {
            if (arg instanceof String) {
                line = arg.toString();
                data = eventProcessor.parse(line);
                newData(data);
            } else if (arg instanceof GameData) {
                newData((GameData) arg);
            } else {
                log.info("not a i/o arg");
            }
            /*
            } else if (o instanceof TelnetEventProcessor) {
            if (arg instanceof GameData) {
            data = (GameData) arg;
            log.info("new data from telnet event processor" + data);
            newData((GameData) data);
            } else {
            log.info("not a telnetevent arg");
            }
             */
            data = null;
        }
    }
    
    public static void main(String[] args) {
        new TelnetConnection();
    }
}
