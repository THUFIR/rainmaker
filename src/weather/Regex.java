package weather;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    private static Logger log = Logger.getLogger(Regex.class.getName());
    private String string = null;
    private String enemy = null;
    private Deque<GameAction> dq = new ArrayDeque<>();
    
    public Regex() {
    }

    private void stripAnsiColors() {
        Pattern regex = Pattern.compile("\\e\\[[0-9;]*m");
        Matcher regexMatcher = regex.matcher(string);
        string = regexMatcher.replaceAll(""); // *3 ??
    }

    public Deque<GameAction> parse(String string) {
        this.string = string;
        ifs();
        return dq;
    }

    private void ifs() {
        if (string.contains("confusing the hell out of")) {
            Pattern pattern = Pattern.compile("(\\w+)");  //(\w+)\.
            Matcher matcher = pattern.matcher(string);
            while (matcher.find()) {
                enemy = matcher.group();
                log.info(enemy);
            }
            backstab();
        }
        if (string.contains("Enter 3-letter city code:")) {
            GameAction washingtonDC = new GameAction("dca");
            dq.add(washingtonDC);
        }
    }

    private void backstab() {
        GameAction backstab = new GameAction("backstab " + enemy);
        dq.add(backstab);
    }
}
