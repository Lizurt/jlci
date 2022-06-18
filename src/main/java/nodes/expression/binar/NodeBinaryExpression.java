package nodes.expression.binar;

import nodes.expression.NodeExpression;

import nodes.Node;
import nodes.expression.NodeExpression;

public abstract class NodeBinaryExpression extends NodeExpression {
    private NodeExpression leftOperand;
    private NodeExpression rightOperand;
    private boolean isANIgnorable;

    public NodeBinaryExpression(NodeExpression leftOperand, NodeExpression rightOperand) {
        this(leftOperand, rightOperand, false);
    }

    public NodeBinaryExpression(NodeExpression leftOperand, NodeExpression rightOperand, boolean isANIgnorable) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        addChild(leftOperand);
        addChild(rightOperand);
        this.isANIgnorable = isANIgnorable;
    }

    public boolean isANIgnorable() {
        return isANIgnorable;
    }

    public void setANIgnorable(boolean ANIgnorable) {
        isANIgnorable = ANIgnorable;
    }

    public NodeExpression getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(NodeExpression leftOperand) {
        this.leftOperand = leftOperand;
        getChildes().set(0, leftOperand);
    }

    public NodeExpression getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(NodeExpression rightOperand) {
        this.rightOperand = rightOperand;
        getChildes().set(1, rightOperand);
    }

    @Override
    public void checkAndFixSemantic() {
        leftOperand.setScope(getScope());
        rightOperand.setScope(getScope());
        leftOperand.checkAndFixSemantic();;
        rightOperand.checkAndFixSemantic();
    }
}
