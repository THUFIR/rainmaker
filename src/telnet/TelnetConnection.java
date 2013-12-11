package telnet;

import game.Context;
import game.RulesForStrategySimple;
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
import model.GameDataBean;

public class TelnetConnection implements Observer {

    private static Logger log = Logger.getLogger(TelnetConnection.class.getName());
    private TelnetClient telnetClient = new TelnetClient();
    private InputOutput inputOutput = new InputOutput();
    private TelnetEventProcessor eventProcessor = new TelnetEventProcessor();
    private RulesForStrategySimple rulesEngine = null;
    private Context context1 = null;
    private GameDataBean oldData = null;
    private GameDataBean newData = null;

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

    private void newTarget() {
        try {
            context1 = new RulesForStrategySimple(oldData).getContext();
            oldData = newData;
            Deque<GameAction> gameActions = context1.executeStrategy();
            while (!gameActions.isEmpty()) {
                GameAction action = gameActions.remove();
                try {
                    sendAction(action);
                } catch (IOException ex) {
                }
            }
        } catch (NullPointerException npe) {
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Class<? extends Object> f = arg.getClass();
        String remoteLineFromGame = null;
        if (o instanceof InputOutput) {
            if (arg instanceof String) {
                remoteLineFromGame = arg.toString();
                newData = (eventProcessor.parse(remoteLineFromGame));
                newTarget();
            } else if (arg instanceof GameDataBean) {
                newData = (GameDataBean) arg;
                newTarget();
            }
        } else {
            log.info("huh?\n" + o.toString());
        }
        remoteLineFromGame = null;
    }

    public static void main(String[] args) {
        new TelnetConnection();
    }
}
