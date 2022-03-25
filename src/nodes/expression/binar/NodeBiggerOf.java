package nodes.expression.binar;

import nodes.Node;

public class NodeBiggerOf extends NodeBinaryExpression {
    public NodeBiggerOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "BIGGER OF";
    }
}
