package nodes.expression.binar;

import nodes.Node;

public class NodeAnyOf extends NodeBinaryExpression {
    public NodeAnyOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "ANY OF";
    }
}
