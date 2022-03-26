package nodes.io;

import nodes.Node;
import nodes.expression.NodeExpression;

public class NodeORly extends Node {

    private Node expression;

    public NodeORly(Node expression) {
        this.expression = expression;
        addChild(expression);
    }

    @Override
    public String toString() {
        return "O RLY?";
    }

    public Node getExpression() {
        return expression;
    }

    public void setExpression(Node expression) {
        this.expression = expression;
        getChildes().set(0, expression);
    }

}
