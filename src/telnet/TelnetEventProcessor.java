package telnet;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.GameData;

public class TelnetEventProcessor {

    private static Logger log = Logger.getLogger(TelnetEventProcessor.class.getName());

    public TelnetEventProcessor() {
    }

    private void stripAnsiColors(String input) {
        Pattern regex = Pattern.compile("\\e\\[[0-9;]*m");
        Matcher regexMatcher = regex.matcher(input);
        String string = regexMatcher.replaceAll(""); // *3 ??
    }

    public GameData parse(String input) {
        //       [\w]+(?=\.) 
        log.fine("checking..");
        GameData gameData = null;
        if (input.contains("confusing the hell out of")) {
            Pattern pattern = Pattern.compile("[\\w]+(?=\\.)");  //(\w+)\.
            Matcher matcher = pattern.matcher(input);
            String enemy = null;
            while (matcher.find()) {
                enemy = matcher.group();
            }
            try {
                gameData = new GameData.Builder().enemy(enemy).build();
            } catch (NullPointerException npe) {
                log.fine(npe.toString());
            }
        } else if (input.contains("ADRENALINE")) {
            log.info("\nadreanline found\n" + input);
            Pattern pattern = Pattern.compile("(\\w+): +(\\S+)");
            Matcher matcher = pattern.matcher(input);
            int hpMin, hpMax, cpMin, cpMax, adr, end, berserk, enemyPerc;
            Map<String, String> monitorMap = new HashMap<>();
            while (matcher.find()) {
                monitorMap.put(matcher.group(1), matcher.group(2));
            }
            for (Map.Entry<String, String> e : monitorMap.entrySet()) {
                String key = e.getKey();
                String val = e.getValue();
                log.info(key + "\t" + val);
            }
            gameData = new GameData.Builder().monitorMap(monitorMap).build();
        } else if (input.contains("Enter 3-letter city code:")) {
            log.fine("found enter city code");
        } else {
        }
        return gameData;
    }
}
