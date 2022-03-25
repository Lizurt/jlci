package nodes.expression.binar;

import nodes.Node;

public class NodeAllOf extends NodeBinaryExpression {
    public NodeAllOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "ALL OF";
    }
}
