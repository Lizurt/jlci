package nodes.io;

import nodes.Node;

public class NodeNoWai extends Node {

    private Node expression;

    public NodeNoWai(Node expression) {
        this.expression = expression;
        addChild(expression);
    }

    @Override
    public String toString() {
        return "NO WAI";
    }

    public Node getExpression() {
        return expression;
    }

    public void setExpression(Node expression) {
        this.expression = expression;
        getChildes().set(0, expression);
    }

}
