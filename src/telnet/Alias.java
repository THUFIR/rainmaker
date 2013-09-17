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

    public AliasTarget parse(String line) throws StringIndexOutOfBoundsException {
        AliasTarget t = new AliasTarget("nobody");
        char c = line.charAt(0);
        String s = String.valueOf(c);
        if ("/".equals(s)) {
            t = getAlias(line);
        }
        return t;
    }

    private AliasTarget getAlias(String line) {
        AliasTarget t = new AliasTarget("nobody");
        Pattern pattern = Pattern.compile("(\\w+)");
        Matcher matcher = pattern.matcher(line);
        List<String> strings = new ArrayList<>();
        while (matcher.find()) {
            strings.add(matcher.group());
        }
        if (1 < strings.size()) {
            if ("t".equals(strings.get(0))) {
                t = new AliasTarget(strings.get(strings.size() - 1));
                log.info(t.toString());
            }
        }
        return t;
    }
}
