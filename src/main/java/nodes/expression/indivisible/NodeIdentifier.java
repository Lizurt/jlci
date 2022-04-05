package nodes.expression.indivisible;

import nodes.expression.NodeExpression;

public class NodeIdentifier extends NodeExpression {
    private String identifier;

    public NodeIdentifier(String identifier) {
        setIdentifier(identifier);
    }

    @Override
    public String toString() {
        return identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
