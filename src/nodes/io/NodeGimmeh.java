package nodes.io;

import nodes.Node;
import nodes.expression.indivisible.NodeIdentifier;

public class NodeGimmeh extends Node {
    private NodeIdentifier identifier;

    public NodeGimmeh(NodeIdentifier identifier) {
        this.identifier = identifier;
        addChild(identifier);
    }

    public NodeIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(NodeIdentifier identifier) {
        this.identifier = identifier;
        getChildes().set(0, identifier);
    }

    @Override
    public String toString() {
        return "GIMMEH";
    }
}
