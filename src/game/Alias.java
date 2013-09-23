package game;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.GameDataBean;

public class Alias {

    private static final Logger log = Logger.getLogger(Alias.class.getName());

    public Alias() {
    }

    public GameDataBean parseUserInput(String line) throws StringIndexOutOfBoundsException {
        GameDataBean gameData = null;
        char c = line.charAt(0);
        String s = String.valueOf(c);
        if ("/".equals(s)) {
            gameData = buildGameData(line);
        }
        return gameData;
    }

    private GameDataBean buildGameData(String line) {
        GameDataBean gameData = null;
        Pattern pattern = Pattern.compile("(\\w+)");
        Matcher matcher = pattern.matcher(line);
        List<String> strings = new ArrayList<>();
        while (matcher.find()) {
            strings.add(matcher.group());
        }
        if (1 < strings.size()) {
            log.fine(strings.toString());
            if ("t".equals(strings.get(0))) {
                String enemy = strings.get(strings.size() - 1);
                gameData = new GameDataBean(enemy);
            }
        }
        return gameData;
    }
}
