package telnet;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alias {

    private static final Logger log = Logger.getLogger(Alias.class.getName());

    public Alias() {
    }

    public String parse(String line) throws StringIndexOutOfBoundsException {
        char c = line.charAt(0);
        String s = String.valueOf(c);
        if ("/".equals(s)) {
            line = getAlias(line);
        }
        return line;
    }

    private String getAlias(String line) {
        Pattern pattern = Pattern.compile("(\\w+)");  //(\w+)\.
        Matcher matcher = pattern.matcher(line);
        List<String> strings = new ArrayList<>();
        while (matcher.find()) {
            strings.add(matcher.group());
        }
        AliasTarget t = new AliasTarget(strings.get(strings.size()-1));
        log.info(t.toString());
        return "";
    }
}
