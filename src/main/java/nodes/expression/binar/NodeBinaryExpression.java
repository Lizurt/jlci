package nodes.expression.binar;

import nodes.Node;
import nodes.expression.NodeExpression;

public abstract class NodeBinaryExpression extends NodeExpression {
    private Node leftOperand;
    private Node rightOperand;
    private boolean isANIgnorable;

    public NodeBinaryExpression(Node leftOperand, Node rightOperand) {
        this(leftOperand, rightOperand, false);
    }

    public NodeBinaryExpression(Node leftOperand, Node rightOperand, boolean isANIgnorable) {
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

    public Node getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(Node leftOperand) {
        this.leftOperand = leftOperand;
        getChildes().set(0, leftOperand);
    }

    public Node getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(Node rightOperand) {
        this.rightOperand = rightOperand;
        getChildes().set(1, rightOperand);
    }
}
