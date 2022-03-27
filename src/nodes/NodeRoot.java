package nodes;

import nodes.expression.PatternConstants;

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
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.HAI);
    }
}
