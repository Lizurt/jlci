package nodes.io;

import nodes.Node;
import nodes.expression.NodeExpression;

public class NodeOic extends Node {

    private NodeExpression expression;

    public NodeOic() {
    }

    public NodeOic(NodeExpression expression) {
        this.expression = expression;
        addChild(expression);
    }

    @Override
    public String toString() {
        return "OIC";
    }

    public NodeExpression getExpression() {
        return expression;
    }

    public void setExpression(NodeExpression expression) {
        this.expression = expression;
        getChildes().set(0, expression);
    }

}
