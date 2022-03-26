package nodes.io;

import nodes.Node;

public class NodeImInYr extends Node {

    private Node expression;

    public NodeImInYr() {
    }

    public NodeImInYr(Node expression) {
        this.expression = expression;
        addChild(expression);
    }

    @Override
    public String toString() {
        return "IM OUTTA YR";
    }

    public Node getExpression() {
        return expression;
    }

    public void setExpression(Node expression) {
        this.expression = expression;
        getChildes().set(0, expression);
    }

}
