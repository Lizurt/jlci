package nodes;

public class NodeRoot extends Node {
    private String version;

    public NodeRoot(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "HAI";
    }
}
