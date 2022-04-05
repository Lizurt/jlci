package nodes.expression.unar;

import nodes.Node;
import nodes.expression.NodeExpression;

public abstract class NodeUnaryExpression extends NodeExpression {
    private Node operand;

    public NodeUnaryExpression(Node operand) {
        this.operand = operand;
        addChild(operand);
    }

    public Node getOperand() {
        return operand;
    }

    public void setOperand(Node operand) {
        this.operand = operand;
        getChildes().set(0, operand);
    }
}
