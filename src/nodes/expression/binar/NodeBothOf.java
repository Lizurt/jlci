package nodes.expression.binar;

import nodes.Node;

public class NodeBothOf extends NodeBinaryExpression {
    public NodeBothOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "BOTH OF";
    }
}
