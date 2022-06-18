package nodes.expression.unar;

import nodes.Node;
import nodes.expression.NodeExpression;

public abstract class NodeUnaryExpression extends NodeExpression {
    private NodeExpression operand;

    public NodeUnaryExpression(NodeExpression operand) {
        this.operand = operand;
        addChild(operand);
    }

    public NodeExpression getOperand() {
        return operand;
    }

    public void setOperand(NodeExpression operand) {
        this.operand = operand;
        getChildes().set(0, operand);
    }

    @Override
    public void checkAndFixSemantic() {
        operand.setScope(getScope());
        operand.checkAndFixSemantic();
    }
}
