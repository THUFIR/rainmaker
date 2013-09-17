package telnet;

public class AliasTarget {

    private String target;
    
    public AliasTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString(){
        return target;
    }
}
