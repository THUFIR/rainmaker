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
    private WeatherActionsSingleton gah = WeatherActionsSingleton.INSTANCE;

    public Regex() {
    }

    private void stripAnsiColors() {
        Pattern regex = Pattern.compile("\\e\\[[0-9;]*m");
        Matcher regexMatcher = regex.matcher(string);
        string = regexMatcher.replaceAll(""); // *3 ??
    }

    public void parse(String string) {
        this.string = string;
        ifs();
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
            WeatherAction washingtonDC = new WeatherAction("dca");
            Deque<WeatherAction> weather = new ArrayDeque<>();
            weather.add(washingtonDC);
            gah.addActions(weather);
        }
    }

    private void backstab() {
        WeatherAction backstab = new WeatherAction("backstab " + enemy);
        Deque<WeatherAction> weather = new ArrayDeque<>();
        weather.add(backstab);
        gah.addActions(weather);
    }
}
