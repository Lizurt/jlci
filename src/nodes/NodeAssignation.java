package nodes;

import nodes.expression.NodeExpression;
import nodes.expression.indivisible.NodeIdentifier;

public class NodeAssignation extends Node {
    private NodeExpression expression;
    private NodeIdentifier identifier;

    public NodeAssignation(NodeIdentifier identifier, NodeExpression expression) {
        this.identifier = identifier;
        this.expression = expression;
        addChild(identifier);
        addChild(expression);
    }

    public NodeExpression getExpression() {
        return expression;
    }

    public void setExpression(NodeExpression expression) {
        this.expression = expression;
        getChildes().set(1, expression);
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
        return "R";
    }
}
