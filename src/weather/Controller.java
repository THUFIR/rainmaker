package weather;

import java.util.logging.Logger;
import telnet.TelnetConnection;

public class Controller {

    private static Logger log = Logger.getLogger(Controller.class.getName());
    private TelnetConnection c = new TelnetConnection();
    private WeatherActionsSingleton gah = WeatherActionsSingleton.INSTANCE;

    public Controller() throws InterruptedException {
        while (true) {
            ActionsDequeuWrapper actions = gah.getActions();
            c.send(actions);
            gah.reset();
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) {
        try {
            new Controller();
        } catch (InterruptedException ex) {
        }
    }
}