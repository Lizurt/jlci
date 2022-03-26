package nodes.io;

import nodes.Node;

public class NodeYr extends Node {

    private Node expression;

    public NodeYr() {
    }

    public NodeYr(Node expression) {
        this.expression = expression;
        addChild(expression);
    }

    @Override
    public String toString() {
        return "YR";
    }

    public Node getExpression() {
        return expression;
    }

    public void setExpression(Node expression) {
        this.expression = expression;
        getChildes().set(0, expression);
    }

}