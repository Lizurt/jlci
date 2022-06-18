package nodes.expression.indivisible.identifiers;

import nodes.expression.NodeExpression;

public abstract class NodeIdentifier extends NodeExpression {
    private String name;

    public NodeIdentifier(String name) {
        setName(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void checkAndFixSemantic() {
        semanticCheckIfExists();
    }

    public abstract void semanticCheckIfExists();

    public abstract void semanticCheckIfNotExists();
}
