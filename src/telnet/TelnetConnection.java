package telnet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Deque;
import java.util.NoSuchElementException;
import org.apache.commons.net.telnet.TelnetClient;
import weather.ActionsDequeuWrapper;
import weather.WeatherAction;

public class TelnetConnection {

    private TelnetClient telnetClient = new TelnetClient();

    public TelnetConnection() {
        try {
            int port = 3000;
            InetAddress host = InetAddress.getByName("rainmaker.wunderground.com");
            telnetClient.connect(host, port);
            IOUtil.readWriteLog(telnetClient.getInputStream(), telnetClient.getOutputStream());
        } catch (SocketException ex) {
        } catch (IOException ex) {
        }
    }

    private void sendAction(WeatherAction action) throws IOException {
        byte[] actionBytes = action.getAction().getBytes();
        OutputStream outputStream = telnetClient.getOutputStream();
        outputStream.write(actionBytes);
        outputStream.write(13);
        outputStream.write(10);
        outputStream.flush();
    }

    private void sendActions(Deque<WeatherAction> ga, long delay) {
        byte[] commandBytes = null;
        OutputStream outputStream = telnetClient.getOutputStream();
        while (!ga.isEmpty()) {
            try {
                WeatherAction action = ga.remove();
                sendAction(action);
                Thread.sleep(delay);
            } catch (InterruptedException | IOException | NoSuchElementException ex) {
            } finally {
            }
        }
    }

    public void send(ActionsDequeuWrapper actions) {
        Deque<WeatherAction> ga = actions.get();
        sendActions(ga,5);
    }
}
