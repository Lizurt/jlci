package nodes.expression.binar;

import nodes.Node;

public class NodeSmallerOf extends NodeBinaryExpression {
    public NodeSmallerOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "SMALLER OF";
    }
}
