package nodes.expression.binar;

import nodes.Node;

public class NodeWonOf extends NodeBinaryExpression {
    public NodeWonOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "WON OF";
    }
}
