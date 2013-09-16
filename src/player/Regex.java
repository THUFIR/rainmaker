package player;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    private static Logger log = Logger.getLogger(Regex.class.getName());
    private String string = null;
    private DataFromRegex data = null;

    public Regex() {
    }

    private void stripAnsiColors() {
        Pattern regex = Pattern.compile("\\e\\[[0-9;]*m");
        Matcher regexMatcher = regex.matcher(string);
        string = regexMatcher.replaceAll(""); // *3 ??
    }

    public DataFromRegex parse(String string) {
        this.string = string;
        ifs();
        log.fine(data.toString());
        return data;
    }

    //       [\w]+(?=\.) 
    private void ifs() {
        log.fine("checking..");
        if (string.contains("confusing the hell out of")) {
            Pattern pattern = Pattern.compile("[\\w]+(?=\\.)");  //(\w+)\.
            Matcher matcher = pattern.matcher(string);
            String enemy = null;
            while (matcher.find()) {
                enemy = matcher.group();
                log.fine(enemy);
                data = new DataFromRegex.Builder().enemy(enemy).build();
            }
        } else if (string.contains("Enter 3-letter city code:")) {
            log.fine("found enter city code");
        } else {
            data = new DataFromRegex.Builder().enemy("null enemy!!!").build();
        }
    }
}
