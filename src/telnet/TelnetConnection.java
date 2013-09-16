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

public class TelnetConnection extends Observable implements Observer {

    private TelnetClient telnetClient = new TelnetClient();
    private InputOutput inputOutput = new InputOutput();
    private Deque<GameAction> gameActions = new ArrayDeque<>();

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

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof InputOutput) {
            setChanged();
            notifyObservers();
            gameActions = inputOutput.getGameActions();
            inputOutput.reset();
        }
    }

    public Deque<GameAction> getGameActions() {
        return gameActions;
    }

    OutputStream getOutputStream() {
        return telnetClient.getOutputStream();
    }
}