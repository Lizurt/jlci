package nodes.io;

import nodes.Node;
import nodes.expression.NodeExpression;

public class NodeYaRly extends Node {

    private Node expression;

    public NodeYaRly(Node expression) {
        this.expression = expression;
        addChild(expression);
    }

    @Override
    public String toString() {
        return "YA RLY";
    }

    public Node getExpression() {
        return expression;
    }

    public void setExpression(Node expression) {
        this.expression = expression;
        getChildes().set(0, expression);
    }

}
