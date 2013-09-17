package telnet;

public class AliasTarget {

    private String target;

    private AliasTarget() {
    }

    public AliasTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return target;
    }
}
