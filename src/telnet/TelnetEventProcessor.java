package telnet;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.GameData;

public class TelnetEventProcessor {

    private static Logger log = Logger.getLogger(TelnetEventProcessor.class.getName());
    private String string = null;

    public TelnetEventProcessor() {
    }

    private void stripAnsiColors() {
        Pattern regex = Pattern.compile("\\e\\[[0-9;]*m");
        Matcher regexMatcher = regex.matcher(string);
        string = regexMatcher.replaceAll(""); // *3 ??
    }

    public GameData parse(String string) {
        this.string = string;
        //       [\w]+(?=\.) 
        log.fine("checking..");
        GameData gameData = null;
        if (string.contains("confusing the hell out of")) {
            Pattern pattern = Pattern.compile("[\\w]+(?=\\.)");  //(\w+)\.
            Matcher matcher = pattern.matcher(string);
            String enemy = null;
            while (matcher.find()) {
                enemy = matcher.group();
            }
            try {
                gameData = new GameData.Builder().enemy(enemy).build();
            } catch (NullPointerException npe) {
                log.severe(npe.toString());
            }

        } else if (string.contains("Enter 3-letter city code:")) {
            log.fine("found enter city code");
        } else {
        }
        return gameData;
    }
}
