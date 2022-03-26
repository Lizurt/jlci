package nodes.io;

import nodes.Node;

public class NodeImOuttaYr extends Node {

    private Node expression;

    public NodeImOuttaYr() {
    }

    public NodeImOuttaYr(Node expression) {
        this.expression = expression;
        addChild(expression);
    }

    @Override
    public String toString() {
        return "IM IN YR";
    }

    public Node getExpression() {
        return expression;
    }

    public void setExpression(Node expression) {
        this.expression = expression;
        getChildes().set(0, expression);
    }

}